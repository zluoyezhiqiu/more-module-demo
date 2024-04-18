package com.yyzy.data.di

import com.yyzy.data.repo.LifeTrackRepository
import com.yyzy.data.repo.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsLifeTrackRepository(
        lifeRepository: LifeTrackRepository,
    ): TrackRepository
}