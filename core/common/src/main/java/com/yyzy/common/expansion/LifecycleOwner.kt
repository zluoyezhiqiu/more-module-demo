package com.yyzy.common.expansion

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yyzy.common.util.LifecycleOwnRegistry
import com.yyzy.common.util.task.GlobalCoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.common.util.task
 * @Description: CODE
 * @Date: 2024/2/1
 */


inline fun <T> CoroutineScope.repeatOnLifecycleOnCoroutineScope(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    launch {
        flow.collectLatest {
            block(it)
        }
    }
}

inline fun <T> LifecycleOwner.repeatOnLifecycleOnCreated(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    repeatOnCreated {
        flow.collectLatest {
            block(it)
        }
    }
}


inline fun <T> LifecycleOwner.repeatOnLifecycleOnResumed(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    repeatOnResumed {
        flow.collectLatest {
            block(it)
        }
    }
}

inline fun <T> LifecycleOwnRegistry.repeatOnCreate(
    state: Lifecycle.State = Lifecycle.State.RESUMED,
    flow: Flow<T>,
    crossinline block: suspend (T) -> Unit
) {
    lifecycleScope.launch (GlobalCoroutineExceptionHandler()){
        repeatOnLifecycle(state) {
            flow.collectLatest {
                block.invoke(it)
            }
        }
    }
}


inline fun LifecycleOwner.repeatOnResumed(crossinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(GlobalCoroutineExceptionHandler()) {
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            block()
        }
    }
}

inline fun LifecycleOwner.repeatOnCreated(crossinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch(GlobalCoroutineExceptionHandler() + Dispatchers.Main) {
        repeatOnLifecycle(Lifecycle.State.CREATED) {
            block()
        }
    }
}