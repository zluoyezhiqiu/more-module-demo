package com.yyzy.common.util.task
import com.yyzy.common.util.LogHelper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * @param errCode 错误码
 * @param errMsg 简要错误信息
 * @param report 是否需要上报
 */
class GlobalCoroutineExceptionHandler(
    private val errCode: Int = -1,
    private val errMsg: String = "",
    private val report: Boolean = false
) : CoroutineExceptionHandler {

    override val key: CoroutineContext.Key<*>
        get() = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        val msg = exception.stackTraceToString()
        LogHelper.e("错误码 = $errCode，GlobalCoroutineExceptionHandler : $msg")
    }
}
