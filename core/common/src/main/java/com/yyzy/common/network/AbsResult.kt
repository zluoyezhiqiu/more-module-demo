package com.yyzy.common.network
open class AbsResult<T, STATUS>(
    val status: STATUS,
    val data: T?,
    val message: String?,
    val exception: Throwable? = null
)