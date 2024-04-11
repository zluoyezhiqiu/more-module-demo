package com.yyzy.common.base

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yyzy.common.util.LogHelper

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.common.base
 * @Description: CODE
 * @Date: 2024/4/11
 */
open class BaseActivity: AppCompatActivity() {

    @Suppress("PropertyName")
    protected val TAG: String = javaClass.simpleName

    override fun onResume() {
        super.onResume()
        orderLog("onResume")
    }

    override fun onRestart() {
        super.onRestart()
        orderLog("onRestart")
    }

    override fun onPause() {
        super.onPause()
        orderLog("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        orderLog("onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        orderLog("onNewIntent LaunchMode")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        orderLog("onRestoreInstanceState")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        orderLog("onConfigurationChanged newConfig-->\n$newConfig")
    }

    private fun orderLog(order: String) {
        LogHelper.d("lifecycle-activity-", "${javaClass.simpleName} $order----->")
    }
}