package com.yyzy.network

import android.util.LruCache
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.yyzy.common.util.LogHelper
import com.yyzy.network.interceptor.RequestInterceptor
import com.yyzy.network.util.OkHttpUtils
import com.yyzy.network.util.PrettyHttpLogger
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.lang.Exception
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.dofun.travel.utils
 * @Description: CODE
 */
object OkHttpCreator {

    private const val OUT_TIME = 60L

    private const val DEFAULT_RETROFIT_SERVICE_MAX_SIZE = 10

    private var appBaseRetrofit: Retrofit by Delegates.observable(createRetrofit()) { _, oldValue, newValue ->
        if (oldValue != newValue){
            retrofitServiceCache.evictAll()
        }
        LogHelper.w("createBaseRetrofit newValue = ${newValue.baseUrl().host} \n oldValue = ${oldValue.baseUrl().host}")
    }

    private val retrofitServiceCache: LruCache<String, Any> by lazy {
        LruCache(DEFAULT_RETROFIT_SERVICE_MAX_SIZE)
    }

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("SERVER_HOST")
            .client(getOkHttpClient())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun createRetrofitHttpLoggingInterceptor() : HttpLoggingInterceptor {
//        val loggingInterceptor = HttpLoggingInterceptor { message ->
//            HttpLogUtils.printFormatContent(message)
//        }
        val loggingInterceptor = HttpLoggingInterceptor(PrettyHttpLogger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    private fun getOkHttpClient(): OkHttpClient {
        val clientBuilder:OkHttpClient.Builder = try {
            OkHttpClient.Builder().sslSocketFactory(
                OkHttpUtils.getIgnoreInitedSslContext().socketFactory,
                OkHttpUtils.IGNORE_SSL_TRUST_MANAGER_X509
            ).hostnameVerifier(OkHttpUtils.getIgnoreSslHostnameVerifier())
        } catch (e: Exception) {
            OkHttpClient.Builder()
        }
        return clientBuilder
            .addInterceptor(RequestInterceptor())
            .addInterceptor(createRetrofitHttpLoggingInterceptor())
            .connectTimeout(OUT_TIME, TimeUnit.SECONDS)
            .callTimeout(OUT_TIME, TimeUnit.SECONDS)
            .readTimeout(OUT_TIME, TimeUnit.SECONDS)
            .writeTimeout(OUT_TIME, TimeUnit.SECONDS)
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        val key = service.simpleName
        @Suppress("UNCHECKED_CAST")
        var retrofitService: T? = retrofitServiceCache.get(key) as T?
        if (retrofitService == null) {
            synchronized(retrofitServiceCache) {
                retrofitService = appBaseRetrofit.create(service)
                retrofitServiceCache.put(key, retrofitService)
            }
        }
        return retrofitService!!
    }
}