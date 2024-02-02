package com.yyzy.network.interceptor
import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        if (!isContainHeader(original, "Content-Type")) {
            builder.addHeader("Content-Type", "application/json")
        }
        if (!isContainHeader(original, "Connection")) {
            builder.addHeader("Connection", "keep-alive")
        }
        if (!isContainHeader(original, "Accept")) {
            builder.addHeader("Accept", "*/*")
        }
        if (!isContainHeader(original, "Origin-Flag")) {
            builder.addHeader("Origin-Flag", "car")
        }
        return chain.proceed(builder.build())
    }

    private fun isContainHeader(request: Request, header: String): Boolean {
        return !TextUtils.isEmpty(request.header(header))
    }
}