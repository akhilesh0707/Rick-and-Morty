package com.aqube.ram.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aqube.ram.cache.dao.CharacterDao
import com.aqube.ram.cache.models.CharacterCacheEntity
import com.aqube.ram.cache.models.CharacterLocationCacheEntity
import com.aqube.ram.cache.utils.CacheConstants
import com.aqube.ram.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [CharacterCacheEntity::class, CharacterLocationCacheEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class CharactersDatabase @Inject constructor() : RoomDatabase() {

    abstract fun cachedCharacterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharactersDatabase? = null

        fun getInstance(context: Context): CharactersDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CharactersDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}
