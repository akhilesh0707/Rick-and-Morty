package com.aqube.ram.di

import com.aqube.ram.BuildConfig
import com.aqube.ram.data.CharacterRepositoryImp
import com.aqube.ram.data.SettingsRepositoryImp
import com.aqube.ram.domain.repository.CharacterRepository
import com.aqube.ram.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(characterRepository: CharacterRepositoryImp): CharacterRepository =
        characterRepository

    @Provides
    @Singleton
    fun provideSettingRepository(): SettingsRepository =
        SettingsRepositoryImp(BuildConfig.VERSION_NAME)
}
