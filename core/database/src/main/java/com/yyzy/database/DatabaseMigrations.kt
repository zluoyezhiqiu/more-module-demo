package com.yyzy.database
import androidx.room.RenameColumn
import androidx.room.migration.AutoMigrationSpec

object DatabaseMigrations {

    @RenameColumn(
        tableName = "life_track",
        fromColumnName = "id",
        toColumnName = "life_id",
    )
    class Schema2to3 : AutoMigrationSpec
}
