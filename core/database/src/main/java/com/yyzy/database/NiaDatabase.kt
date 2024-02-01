package com.yyzy.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yyzy.database.dao.LifeTrackDAO
import com.yyzy.database.model.LifeTrackEntity
import com.yyzy.database.util.ClassAndGradleConverter
import com.yyzy.database.util.ListConverter

@Database(
    entities = [
        LifeTrackEntity::class,
    ],
    version = 1,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = DatabaseMigrations.Schema2to3::class),
    ],
    exportSchema = true,
)
@TypeConverters(
    ListConverter::class,
    ClassAndGradleConverter::class,
)
abstract class NiaDatabase : RoomDatabase() {
    abstract fun lifeTrackDAO(): LifeTrackDAO
}
