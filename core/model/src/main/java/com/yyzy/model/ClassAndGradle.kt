package com.yyzy.model

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.data.model
 * @Description: CODE
 * @Date: 2024/2/1
 */
enum class ClassAndGradle(
    val serializedName: String,
    val classGradle: String,
    val school: String,
    val detail: String
) {
    TIE_TANG(
        serializedName = "TIE_TANG",
        classGradle = "0",
        school = "铁塘",
        detail = "TIE_TANG"
    ),
    FU_RONG(
        serializedName = "FU_RONG",
        classGradle = "-100",
        school = "芙蓉",
        detail = "FU_RONG"
    ),
    QI_ZHONG(
        serializedName = "QI_ZHONG",
        classGradle = "100",
        school = "七中",
        detail = "QI_ZHONG"
    ),
    YUE_YANG(
        serializedName = "YUE_YANG",
        classGradle = "200",
        school = "岳阳",
        detail = "YUE_YANG"
    ),
    Unknown(
        serializedName = "Unknown",
        classGradle = "Unknown",
        school = "Unknown",
        detail = "Unknown",
    ),
}

fun String?.asClassAndGradle() = when (this) {
    null -> ClassAndGradle.Unknown
    else -> ClassAndGradle.values()
        .firstOrNull { type -> type.serializedName == this }
        ?: ClassAndGradle.Unknown
}
