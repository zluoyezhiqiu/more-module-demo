package com.yyzy.common.util.task.executor

interface ExecutorServiceManager {
    fun cancel(r: Runnable): Boolean
    fun remove(r: Runnable)
    fun execute(r: Runnable)
}