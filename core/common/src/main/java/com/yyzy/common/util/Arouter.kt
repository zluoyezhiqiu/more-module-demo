package com.yyzy.common.util

import com.alibaba.android.arouter.launcher.ARouter

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.common.util
 * @Description: CODE
 * @Date: 2024/3/4
 */
fun String.startNavigation() {
    if (checkPath(this)) {
        ARouter.getInstance()
            .build(this)
            .navigation()
    }
}

private fun checkPath(path: String): Boolean {
    return path.contains("/")
}

fun String.withDataNavigation(vararg pair: Pair<String, Any>) {
    if (checkPath(this) && pair.isNotEmpty()) {
        val postcard = ARouter.getInstance().build(this)
        pair.forEach { data ->
            when (data.second) {
                is String -> postcard.withString(data.first, data.second as String)
                is Int -> postcard.withInt(data.first, data.second as Int)
                is Long -> postcard.withLong(data.first, data.second as Long)
            }
        }
        postcard.navigation()
    }
}
