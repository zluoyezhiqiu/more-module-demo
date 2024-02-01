package com.yyzy.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.yyzy.model.ClassAndGradle

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.database.model
 * @Description: CODE
 * @Date: 2024/2/1
 */
@Entity(
    tableName = "life_track",
    primaryKeys = ["id"]
)
data class LifeTrackEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "like")
    val like: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "character")
    val character: String,
    @ColumnInfo(name = "life_track")
    val lifeTrack: List<String>,
    @ColumnInfo(name = "class_gradle")
    val classGradle: ClassAndGradle,
){
    override fun toString(): String {
        return "LifeTrackEntity(id='$id'," +
                "like='$like', " +
                "type='$type', " +
                "character='$character', \n" +
                "lifeTrack=$lifeTrack, \n" +
                "classGradle=$classGradle)"
    }
}