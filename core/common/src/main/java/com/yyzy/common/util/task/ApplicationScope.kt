package com.yyzy.common.util.task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

object ApplicationScope: Executor, CoroutineScope by CoroutineScope(Dispatchers.IO + SupervisorJob()) {
    override fun execute(command: Runnable?) {
        command?.run { launch { run() } }
    }
}

inline fun requestApplicationMain(crossinline taskCallback: suspend () -> Unit) {
    ApplicationScope.launch(GlobalCoroutineExceptionHandler() + Dispatchers.Main) {
        taskCallback.invoke()
    }
}

inline fun requestApplicationIO(crossinline taskCallback: suspend () -> Unit) {
    ApplicationScope.launch(GlobalCoroutineExceptionHandler() + Dispatchers.IO +  SupervisorJob()) {
        taskCallback.invoke()
    }
}

inline fun <T> repeatOnViewLifecycleOnAppMain(
    flow: Flow<T>,
    crossinline block: suspend (T) -> Unit
) {
    requestApplicationMain {
        flow.collectLatest {
            block(it)
        }
    }
}