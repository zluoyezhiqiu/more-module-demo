package com.yyzy.common.expansion

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

/**
 * @author ljl
 * @date 2024/9/19 22:23
 * @Package com.yyzy.common.expansion
 * @Description: 作用与 fragment 中 view的生命周期
 */
inline fun <T> Fragment.repeatOnViewLifecycleOnCreated(
    flow: Flow<T>,
    crossinline block: suspend CoroutineScope.(T) -> Unit
) {
    viewLifecycleOwner.repeatOnCreated {
        flow.collectLatest {
            block(it)
        }
    }
}