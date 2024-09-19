package com.yyzy.common.util.executor

import android.os.Handler
import android.os.Looper

object MainExecutor : ExecutorManger {

    private val mainLooper = Looper.getMainLooper()

    private val mExecutor = Handler(mainLooper)

    override fun execute(r: Runnable) {
        if (mainLooper === Looper.myLooper()) {
            r.run()
        } else {
            mExecutor.post(r)
        }
    }

    override fun executeDelayed(delayMillis: Long, command: Runnable) {
        mExecutor.postDelayed(command, delayMillis)
    }

    override fun cancel(command: Runnable) {
        mExecutor.removeCallbacks(command)
    }
}