package com.aqube.ram.cache

import android.util.Log
import com.aqube.ram.cache.dao.CharacterDao
import com.aqube.ram.cache.mapper.CharacterCacheMapper
import com.aqube.ram.cache.utils.PreferencesHelper
import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.repository.CharacterCache
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterCacheImp @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterCacheMapper: CharacterCacheMapper,
    private val preferencesHelper: PreferencesHelper
) : CharacterCache {

    override suspend fun getCharacters(): Flow<List<CharacterEntity>> = flow {
        characterDao.getCharacters().collect { cacheList ->
            emit(cacheList.map { cacheCharacter ->
                characterCacheMapper.mapFromCached(cacheCharacter)
            })
        }
    }

    override suspend fun getCharacter(characterId: Long): Flow<CharacterEntity> = flow {
        characterDao.getCharacter(characterId).collect { cacheCharacter ->
            emit(characterCacheMapper.mapFromCached(cacheCharacter))
        }
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        try {
            characterDao.addCharacter(
                *listCharacters.map {
                    characterCacheMapper.mapToCached(it)
                }.toTypedArray()
            )
        } catch (e: Exception) {
            Log.d("22222222222222222", e.message.toString())
        }

    }

    override suspend fun getBookMarkedCharacters(): Flow<List<CharacterEntity>> = flow {
        characterDao.getBookMarkedCharacters().collect { cacheList ->
            cacheList.map { cacheCharacter ->
                characterCacheMapper.mapFromCached(cacheCharacter)
            }.asFlow()
        }
    }

    override suspend fun setCharacterBookmarked(characterId: Long): Flow<Int> = flow {
        emit(characterDao.bookmarkCharacter(characterId))
    }

    override suspend fun setCharacterUnBookMarked(characterId: Long): Flow<Int> = flow {
        emit(characterDao.unBookmarkCharacter(characterId))
    }

    override suspend fun isCached(): Boolean {
        val isEmpty = characterDao.getCharacters().map {
            return@map it.isNullOrEmpty()
        }
        return isEmpty.first()
    }

    override suspend fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override suspend fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = this.getLastCacheUpdateTimeMillis()
        return currentTime - lastUpdateTime > EXPIRATION_TIME
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferencesHelper.lastCacheTime
    }

    companion object {
        /**
         * Expiration time set to 5 minutes
         */
        const val EXPIRATION_TIME = (60 * 5 * 1000).toLong()
    }

}