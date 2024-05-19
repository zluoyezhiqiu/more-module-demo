package com.yyzy.common.util

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * @author ljl
 * @date 2024/5/19 19:25
 * @Package com.yyzy.main
 * @Description: 暂无描述
 */
class LifecycleOwnRegistry(
    tag: String? = null
) : LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.addObserver(object : DefaultLifecycleObserver {

        })
        tag?.let {
            lifecycleRegistry.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    LogHelper.d(it, "onStateChanged currentEvent --> ${event.name}")
                }
            })
        }
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry
}


