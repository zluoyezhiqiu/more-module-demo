package com.yyzy.common.util

import android.util.Log

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @Description: CODE
 * @Date: 2023/8/4
 */
object LogHelper {

    private const val TAG = "LogHelper"

    @JvmStatic
    fun d(msg: String) {
        Log.d(TAG, msg.getFormatStr())
    }

    @JvmStatic
    fun e(msg: String) {
        Log.e(TAG, msg.getFormatStr())
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        Log.d(tag + TAG, msg.getFormatStr())
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        Log.w(tag + TAG, msg.getFormatStr())
    }

    @JvmStatic
    fun w(msg: String) {
        Log.w(TAG, msg.getFormatStr())
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        Log.e(tag + TAG, msg.getFormatStr())
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        Log.i(tag + TAG, msg.getFormatStr())
    }

    private fun String.getFormatStr(): String {
        return ">>>*** $this ***<<<"
    }
}