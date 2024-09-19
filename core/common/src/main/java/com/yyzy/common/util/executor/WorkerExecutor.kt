package com.yyzy.common.util.executor

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

object WorkerExecutor : ExecutorManger {

    private val mExecutor: Handler
    private val mHandlerThread = HandlerThread("WorkerExecutor")

    private val workerLooper: Looper
    val handler:Handler
        get() {
            return mExecutor
        }

    init {
        mHandlerThread.start()
        val looper = mHandlerThread.looper
        mExecutor = Handler(looper)
        workerLooper = looper
    }

    override fun executeDelayed(delayMillis: Long, command: Runnable) {
        mExecutor.postDelayed(command, delayMillis)
    }

    override fun cancel(command: Runnable) {
        mExecutor.removeCallbacks(command)
    }

    override fun execute(command: Runnable) {
        mExecutor.post(command)
    }
}