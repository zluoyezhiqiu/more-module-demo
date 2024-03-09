package com.yyzy.data.model

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.network.bean
 * @Description: CODE
 * @Date: 2024/3/9
 */
@Serializable
data class SearchParameters(
    val searchQuery: String,
    val filters: List<String>
)

val SearchParametersType = object : NavType<SearchParameters>(
    isNullableAllowed = false
) {
    override fun put(bundle: Bundle, key: String, value: SearchParameters) {
        val string = Json.encodeToString(value)
        bundle.putString(key, string)
    }
    override fun get(bundle: Bundle, key: String): SearchParameters? {
        return bundle.getString(key)?.let {
            Json.decodeFromString<SearchParameters>(it)
        }
    }
    override fun parseValue(value: String): SearchParameters {
        return Json.decodeFromString<SearchParameters>(value)
    }
    // Only required when using Navigation 2.4.0-alpha07 and lower
    override val name = "SearchParameters"
}