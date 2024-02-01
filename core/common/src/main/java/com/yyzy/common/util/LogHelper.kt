package com.yyzy.common.util
import android.util.Log

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: cn.cardoor.app.basic.util
 * @Description: CODE
 * @Date: 2023/8/4
 */
object LogHelper {

    private const val commentTag = "LogHelper"

    @JvmStatic
    fun d(msg: String) {
        Log.d(commentTag, msg.getFormatStr())
    }

    @JvmStatic
    fun dOne(msg: String) {
        Log.d(commentTag + "_Class1", msg.getFormatStr())
    }

    @JvmStatic
    fun dTwo(msg: String) {
        Log.d(commentTag + "_Class2", msg.getFormatStr())
    }

    @JvmStatic
    fun dThree(msg: String) {
        Log.d( "Log_Class3", msg.getFormatStr())
    }

    @JvmStatic
    fun e(msg: String) {
        Log.e(commentTag, msg.getFormatStr())
    }

    @JvmStatic
    fun d(tag: String, msg: String) {
        Log.d(tag + commentTag, msg.getFormatStr())
    }

    @JvmStatic
    fun w(tag: String, msg: String) {
        Log.w("$tag-$commentTag[warn]", msg.getFormatStr())
    }

    @JvmStatic
    fun w(msg: String) {
        Log.w("$commentTag[warn]", msg.getFormatStr())
    }

    @JvmStatic
    fun e(tag: String, msg: String) {
        Log.e(tag + commentTag, msg.getFormatStr())
    }

    @JvmStatic
    fun i(tag: String, msg: String) {
        Log.i(tag + commentTag, msg.getFormatStr())
    }

    private fun String.getFormatStr(): String {
        return ">>>*** $this ***<<<"
    }
}