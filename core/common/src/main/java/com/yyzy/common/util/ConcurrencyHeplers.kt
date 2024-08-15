package com.yyzy.common.util

import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.yield
import java.util.concurrent.atomic.AtomicReference

class SingleRunner {
    private val mutex = Mutex()
    suspend fun <T> afterPrevious(block: suspend () -> T): T {
        mutex.withLock {
            return block()
        }
    }
}

class ControlledRunner<T> {

    private val activeTask = AtomicReference<Deferred<T>?>(null)

    /**
     * 取消之前的任务，运行最新的任务
     * @param block SuspendFunction0<T>
     *
     * class Products {
     *    val controlledRunner = ControlledRunner<Product>()
     *
     *    fun sortAscending(): List<Product> {
     *        return controlledRunner.cancelPreviousThenRun { dao.loadSortedAscending() }
     *    }
     *
     *    fun sortDescending(): List<Product> {
     *        return controlledRunner.cancelPreviousThenRun { dao.loadSortedDescending() }
     *    }
     * }
     * @return T 结果
     *
     */
    suspend fun cancelPreviousThenRun(block: suspend () -> T): T {
        activeTask.get()?.cancelAndJoin()
        return coroutineScope {
            val newTask = async(start = LAZY) {
                block()
            }
            newTask.invokeOnCompletion {
                activeTask.compareAndSet(newTask, null)
            }
            val result: T
            while (true) {
                if (!activeTask.compareAndSet(null, newTask)) {
                    activeTask.get()?.cancelAndJoin()
                    yield()
                } else {
                    result = newTask.await()
                    break
                }
            }
            result
        }
    }

    /**
     * 继续运行旧的协程，取消新的协程
     * @param block SuspendFunction0<T>
     * @return T 结果
     *
     * class Products {
     *    val controlledRunner = ControlledRunner<Product>()
     *
     *    fun fetchProducts(): List<Product> {
     *        return controlledRunner.joinPreviousOrRun {
     *            val results = api.fetchProducts()
     *            dao.insert(results)
     *            results
     *        }
     *    }
     * }
     */
    suspend fun joinPreviousOrRun(block: suspend () -> T): T {
        //如果有旧任务则直接执行并返回结果
        val get = activeTask.get()
        get?.let {
            return it.await()
        }
        return coroutineScope {
            //使目标方法延迟执行
            val newTask = async(start = LAZY) {
                block()
            }
            //在当前协程执行完成之后置空，便于循环进入下一个流程
            newTask.invokeOnCompletion {
                activeTask.compareAndSet(newTask, null)
            }
            val result: T
            //使用死循环使本次方法达到目的之后在结束
            while (true) {
                //判断当前是否有任务正在运行，如果没有，则将新任务存放起来
                if (!activeTask.compareAndSet(null, newTask)) {
                    //执行到里面就代表有任务正在执行
                    val currentTask = activeTask.get()
                    if (currentTask != null) {
                        //取消执行新的任务，并尝试直接返回本次方法的结果
                        newTask.cancel()
                        result = currentTask.await()
                        break
                    } else {
                        //这里处理并发情况
                        yield()
                    }
                } else {
                    //执行最前的任务
                    result = newTask.await()
                    break
                }
            }
            result
        }
    }

    fun cancelCurrent() {
        activeTask.get()?.cancel()
    }

    fun alreadyRunning() = activeTask.get() == null
}