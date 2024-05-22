package com.yyzy.common.util.task.executor

import java.util.concurrent.Executor

interface ExecutorManger : Executor {
    fun executeDelayed(delayMillis: Long, command: Runnable)

    fun cancel(command: Runnable)
}