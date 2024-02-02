package com.yyzy.test.other.poxy

import android.os.SystemClock
import com.yyzy.base.LogHelper
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.test.other.poxy
 * @Description: CODE
 * @Date: 2024/1/18
 */
object AnxietyRoleSay {

    private val anxietyRole1 by lazy {
        createDaoProxy(AnxietyRole1() as AnxietyInterface)
    }

    private val anxietyRole2 by lazy {
        createDaoProxy(AnxietyRole2() as AnxietyInterface)
    }

    fun say() {
        arrayOf(anxietyRole1, anxietyRole2).forEach { anxietyInterface ->
            anxietyInterface.qiDuan()
            anxietyInterface.touYun()
            anxietyInterface.buNengJiZhongZhuYiLi()
        }
    }

    private inline fun <reified T> createDaoProxy(tag: T): T {
        return Proxy.newProxyInstance(
            T::class.java.classLoader, arrayOf(T::class.java), AnxietyProxy(tag)
        ) as T
    }

    private class AnxietyProxy(private val target: Any?) : InvocationHandler {
        private val logTag: String = "Anxiety"
        override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
            val start = SystemClock.elapsedRealtime()
            val invoke = method.invoke(target, *args ?: emptyArray())
            LogHelper.i(logTag, "Anxiety ${method.name} ${SystemClock.elapsedRealtime() - start}ms")
            return invoke
        }
    }
}