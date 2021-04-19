package com.aqube.ram.di

import com.aqube.ram.data.CharacterRepositoryImp
import com.aqube.ram.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(characterRepository: CharacterRepositoryImp): CharacterRepository {
        return characterRepository
    }

}
