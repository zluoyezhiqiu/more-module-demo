package com.yyzy.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yyzy.database.model.LifeTrackEntity
import kotlinx.coroutines.flow.Flow

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName: com.yyzy.database.dao
 * @Description: CODE
 * @Date: 2024/2/1
 */
@Dao
interface LifeTrackDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(newsResources: List<LifeTrackEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg resource: LifeTrackEntity)

    @Query(value = "SELECT * FROM life_track")
    fun searchAllLifeTrackEntity(): Flow<List<LifeTrackEntity>>

    @Query("SELECT count(*) FROM life_track")
    fun getCount(): Flow<Int>
}