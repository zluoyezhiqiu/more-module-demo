package com.yyzy.common.expansion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yyzy.common.util.task.GlobalCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author ljl
 * @date 2024/9/19 22:26
 * @Package com.yyzy.common.expansion
 * @Description: 暂无描述
 */

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

inline fun <T> ViewModel.repeatOnLifecycleOnViewModel(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    viewModelScope.launch {
        flow.collectLatest {
            block(it)
        }
    }
}