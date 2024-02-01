package com.yyzy.common
import android.app.Application
import kotlin.properties.Delegates


/**
 * Description:
 * author: xianjielee
 * date: 2020/8/21 11:44
 */
abstract class BasicApplication : Application() {

    companion object {

        var appContext: BasicApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}