package com.yyzy.common.util.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

inline fun ViewModel.requestMain(
    errCode: Int = -1, errMsg: String = "", report: Boolean = false,
    crossinline block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        block.invoke(this)
    }
}

fun ViewModel.requestIO(
    errCode: Int = -1, errMsg: String = "", report: Boolean = false,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(
        Dispatchers.IO +
                GlobalCoroutineExceptionHandler(errCode, errMsg, report)
    ) {
        block.invoke(this)
    }
}

fun ViewModel.delayMain(
    errCode: Int = -1, errMsg: String = "", report: Boolean = false, delayTime: Long,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(GlobalCoroutineExceptionHandler(errCode, errMsg, report)) {
        withContext(Dispatchers.IO) {
            delay(delayTime)
        }
        block.invoke(this)
    }
}