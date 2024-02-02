package com.yyzy.network.bean

import java.io.Serializable

data class BaseReposeBean<T>(
    val code: String,
    val msg: String,
    val success: Boolean,
    val data: T
) : Serializable
