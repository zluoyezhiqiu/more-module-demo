package com.yyzy.main

import com.alibaba.android.arouter.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import com.yyzy.common.BasicApplication
import com.yyzy.sync.work.initializers.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : BasicApplication() {

    override fun onCreate() {
        super.onCreate()
        initARouter()
        // Initialize Sync; the system responsible for keeping data in the app up to date.
        Sync.initialize(context = this)
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}