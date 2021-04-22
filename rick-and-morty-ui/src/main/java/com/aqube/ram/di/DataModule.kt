package com.aqube.ram.di

import com.aqube.ram.data.CharacterRepositoryImp
import com.aqube.ram.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideCharacterRepository(characterRepository: CharacterRepositoryImp): CharacterRepository

}
