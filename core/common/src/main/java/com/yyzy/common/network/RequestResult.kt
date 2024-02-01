package com.yyzy.common.network

class RequestResult<T>(
    status: Status,
    data: T?,
    message: String?,
    exception: Throwable? = null
) : AbsResult<T, Status>(status, data, message, exception) {

    fun isSuccess(): Boolean = status == Status.SUCCESS
    fun isError(): Boolean = status == Status.ERROR
    fun isLoading(): Boolean = status == Status.LOADING
    fun isEmpty(): Boolean = status == Status.EMPTY
    fun isInit(): Boolean = status == Status.Init

    companion object {
        fun <T> create(
            status: Status,
            data: T?,
            message: String? = null,
            exception: Throwable? = null
        ): RequestResult<T> =
            RequestResult(status, data, message, exception)

        fun <T> success(data: T?, msg: String? = null): RequestResult<T> =
            RequestResult(Status.SUCCESS, data, msg)

        fun <T> error(msg: String?, data: T? = null): RequestResult<T> =
            RequestResult(Status.ERROR, data, msg)

        fun <T> error(msg: String?, data: T?, error: Throwable? = null): RequestResult<T> =
            RequestResult(Status.ERROR, data, msg, error)

        fun <T> loading(msg: String? = null): RequestResult<T> =
            RequestResult(Status.LOADING, null, msg)

        fun <T> empty(): RequestResult<T> = RequestResult(Status.EMPTY, null, null)

        fun <T> init(): RequestResult<T> = RequestResult(Status.Init, null, null)
    }

    override fun toString(): String {
        return "Result(status=$status, message=$message, data=$data)"
    }
}