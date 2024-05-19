package com.yyzy.common.util.dsl

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.yyzy.common.util.LifecycleOwnRegistry
import com.yyzy.common.util.task.GlobalCoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author ljl
 * @date 2024/5/19 19:32
 * @Package com.yyzy.common.util.dsl
 * @Description: 暂无描述
 */

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
