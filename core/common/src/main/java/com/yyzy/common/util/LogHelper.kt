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
        Log.d(TAG, msg)
    }

    @JvmStatic
    fun e(msg: String) {
        Log.e(TAG, msg)
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        Log.d(tag + TAG, msg)
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        Log.w(tag + TAG, msg)
    }

    @JvmStatic
    fun w(msg: String) {
        Log.w(TAG, msg)
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        Log.e(tag + TAG, msg)
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        Log.i(tag + TAG, msg)
    }

    private const val MAX_LOG_LENGTH = 4000

    fun logLongMessage(tag: String, message: String) {
        if (message.length > MAX_LOG_LENGTH) {
            for (i in 0..message.length / MAX_LOG_LENGTH) {
                val start = i * MAX_LOG_LENGTH
                var end = (i + 1) * MAX_LOG_LENGTH
                end = if (end > message.length) message.length else end
                Log.d(tag, message.substring(start, end))
            }
        } else {
            Log.d(tag, message)
        }
    }
}