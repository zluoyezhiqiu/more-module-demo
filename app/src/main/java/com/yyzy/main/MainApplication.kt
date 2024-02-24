package com.yyzy.main
import com.yyzy.common.BasicApplication
import com.yyzy.sync.work.initializers.Sync
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : BasicApplication(){

    override fun onCreate() {
        super.onCreate()
        // Initialize Sync; the system responsible for keeping data in the app up to date.
        Sync.initialize(context = this)
    }

}