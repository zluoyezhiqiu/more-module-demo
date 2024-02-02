@file:JvmName("FlowUtils")

package com.yyzy.network.util
import com.yyzy.common.network.RequestResult
import com.yyzy.common.util.LogHelper
import com.yyzy.network.bean.BaseReposeBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlin.coroutines.CoroutineContext

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.common.util.task
 * @Description: CODE
 * @Date: 2024/2/2
 */

fun <T> Flow<BaseReposeBean<T>>.toResource(
    defaultDispatcher: CoroutineContext = Dispatchers.IO,
    isLoading: Boolean = false
): Flow<RequestResult<T>> = flow {
    flowOn(defaultDispatcher).onStart {
        if (isLoading) emit(RequestResult.loading())
    }.catch {
        LogHelper.e("toResource catch message [${it.message}]")
        emit(RequestResult.error(null, null, error = it))
    }.map { request ->
        if (request.success) {
            RequestResult.success(data = request.data, msg = request.msg)
        } else {
            RequestResult.error(msg = request.code, data = request.data)
        }
    }.collect {
        emit(it)
    }
}

inline fun <T> requestNetworkData(
    crossinline dataBlock: suspend () -> BaseReposeBean<T>,
) = flow {
    emit(dataBlock())
}.toResource()
    .filter { !it.isLoading() }
    .map {
        if (it.isSuccess()) {
            it.data
        } else {
            null
        }
    }

inline fun <T, D> Flow<RequestResult<T>>.coverResource(
    crossinline customFlow: (RequestResult<T>) -> D
) =
    flow {
        map {
            customFlow(it)
        }.collect {
            emit(it)
        }
    }