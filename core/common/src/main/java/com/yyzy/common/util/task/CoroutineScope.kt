package com.yyzy.common.util.task

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
inline fun <T> CoroutineScope.repeatOnViewLifecycleOnComponent(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    launch {
        flow.collectLatest {
            block(it)
        }
    }
}

inline fun <T> LifecycleOwner.repeatOnViewLifecycleOnCreated(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    repeatOnCreated {
        flow.collectLatest {
            block(it)
        }
    }
}

inline fun <T> LifecycleOwner.repeatOnViewLifecycleOnResumed(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    repeatOnResumed {
        flow.collectLatest {
            block(it)
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