package com.aqube.ram.di

import android.content.Context
import com.aqube.ram.cache.CharacterCacheImp
import com.aqube.ram.cache.dao.CharacterDao
import com.aqube.ram.cache.database.CharacterDatabase
import com.aqube.ram.cache.utils.PreferencesHelper
import com.aqube.ram.data.repository.CharacterCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CacheModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return CharacterDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideCharacterDao(characterDatabase: CharacterDatabase): CharacterDao {
        return characterDatabase.cachedCharacterDao()
    }

    @Provides
    @Singleton
    fun provideCharacterCache(characterCache: CharacterCacheImp): CharacterCache {
        return characterCache
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(@ApplicationContext context: Context): PreferencesHelper {
        return PreferencesHelper(context)
    }
}
