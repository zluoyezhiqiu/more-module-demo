package com.yyzy.network.util

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.yyzy.common.util.LogHelper
import okhttp3.logging.HttpLoggingInterceptor
import org.intellij.lang.annotations.Language

object PrettyHttpLogger : HttpLoggingInterceptor.Logger {

    private const val TAG = "HttpLogger"

    private const val LOG_HTTP_END_FLAG = "<-- END HTTP"

    private const val MESSAGE_MAX_LENGTH = 4076 - 70

    @Language("regexp")
    private const val LOG_HTTP_START_PATTERN =
        "^-->\\s+(POST|GET|PUT|PATCH|DELETE|COPY|HEAD|OPTIONS|LINK|UNLINK|PURGE|CONNECT|TRACE|VIEW)\\s+http.*"

    private val logHttpStartRegex = LOG_HTTP_START_PATTERN.toRegex()

    private val sb = StringBuilder()

    private val gson by lazy {
        GsonBuilder().setPrettyPrinting().create()
    }

    override fun log(message: String) {
        when {
            logHttpStartRegex in message -> {
                sb.clear()
                sb.append(message)
            }

            message.startsWith(LOG_HTTP_END_FLAG) -> {
                sb.append(message)
                internalLog(sb.toString())
                return
            }

            else -> sb.simpleAppend(message)
        }

        sb.appendLine()
    }

    private fun internalLog(msg: String) {
        if (msg.length > MESSAGE_MAX_LENGTH) {
            msg.chunked(MESSAGE_MAX_LENGTH).forEach { chunk ->
                LogHelper.d(TAG, chunk)
            }
        } else LogHelper.d(TAG, msg)
    }

    private fun StringBuilder.simpleAppend(msg: String) {
        if (msg.isBlank()) append(msg)
        else try {
            gson.toJson(JsonParser.parseString(msg), this)
        } catch (_: Exception) {
            append(msg)
        }
    }
}
