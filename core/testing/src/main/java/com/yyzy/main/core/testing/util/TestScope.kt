package com.yyzy.main.core.testing.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


inline fun <T> CoroutineScope.repeatOnTestScope(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit,
): Job = launch {
    flow.collectLatest {
        block(it)
    }
}
