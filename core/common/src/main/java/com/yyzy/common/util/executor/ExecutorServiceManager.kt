package com.yyzy.common.util.executor

interface ExecutorServiceManager {
    fun cancel(r: Runnable): Boolean
    fun remove(r: Runnable)
    fun execute(r: Runnable)
}