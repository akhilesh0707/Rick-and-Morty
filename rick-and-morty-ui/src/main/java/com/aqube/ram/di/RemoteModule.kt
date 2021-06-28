package com.aqube.ram.di

import com.aqube.ram.BuildConfig
import com.aqube.ram.data.repository.CharacterRemote
import com.aqube.ram.remote.api.CharacterService
import com.aqube.ram.remote.api.ServiceFactory
import com.aqube.ram.remote.repository.CharacterRemoteImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideBlogService(): CharacterService {
        return ServiceFactory.create(BuildConfig.DEBUG, BuildConfig.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideCharacterRemote(characterRemote: CharacterRemoteImp): CharacterRemote {
        return characterRemote
    }
}
