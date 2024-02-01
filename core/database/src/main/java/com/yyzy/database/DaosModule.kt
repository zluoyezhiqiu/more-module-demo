package com.yyzy.database

import com.yyzy.database.dao.LifeTrackDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesLifeTrackDao(
        database: NiaDatabase,
    ): LifeTrackDAO = database.lifeTrackDAO()
}
