package com.aqube.ram.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aqube.ram.cache.dao.CharacterDao
import com.aqube.ram.cache.models.CharacterCache
import com.aqube.ram.cache.models.CharacterLocationCache
import com.aqube.ram.cache.utils.CacheConstants
import com.aqube.ram.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [CharacterCache::class, CharacterLocationCache::class],
    version = Migrations.DB_VERSION
)
abstract class RickMortyDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedCharacterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: RickMortyDatabase? = null

        fun getInstance(context: Context): RickMortyDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            RickMortyDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}