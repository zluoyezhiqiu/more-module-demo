package com.yyzy.database.util

import androidx.room.TypeConverter
import com.yyzy.model.ClassAndGradle
import com.yyzy.model.asClassAndGradle

class ListConverter {
    companion object {
        private const val KEY = "listToSting"
    }

    @TypeConverter
    fun listToSting(value: List<String>?): String? =
        value?.let {
            it.joinToString { content ->
                content + KEY
            }
        }

    @TypeConverter
    fun stringToList(value: String?): List<String>? = value?.split(KEY)
}


class ClassAndGradleConverter {
    @TypeConverter
    fun classAndGradleToString(value: ClassAndGradle?): String? =
        value?.let(ClassAndGradle::serializedName)

    @TypeConverter
    fun stringToClassAndGradle(serializedName: String?): ClassAndGradle? =
        serializedName?.asClassAndGradle()
}
