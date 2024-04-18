package com.yyzy.main.core.testing
import com.yyzy.data.di.DataModule
import com.yyzy.data.repo.TrackRepository
import com.yyzy.main.core.testing.repo.TestLifeTrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class],
)
interface TestDataModule {
    @Binds
    fun bindsLifeTrackRepository(
        lifeRepository: TestLifeTrackRepository,
    ): TrackRepository
}
