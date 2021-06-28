package com.aqube.ram.cache

import com.aqube.ram.cache.dao.CharacterDao
import com.aqube.ram.cache.mapper.CharacterCacheMapper
import com.aqube.ram.cache.utils.CachePreferencesHelper
import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.repository.CharacterCache
import javax.inject.Inject

class CharacterCacheImp @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterCacheMapper: CharacterCacheMapper,
    private val preferencesHelper: CachePreferencesHelper
) : CharacterCache {

    override suspend fun getCharacters(): List<CharacterEntity> {
        return characterDao.getCharacters().map { cacheCharacter ->
            characterCacheMapper.mapFromCached(cacheCharacter)
        }
    }

    override suspend fun getCharacter(characterId: Long): CharacterEntity {
        return characterCacheMapper.mapFromCached(characterDao.getCharacter(characterId))
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        characterDao.addCharacter(
            *listCharacters.map {
                characterCacheMapper.mapToCached(it)
            }.toTypedArray()
        )
    }

    override suspend fun getBookMarkedCharacters(): List<CharacterEntity> {
        return characterDao.getBookMarkedCharacters().map { cacheCharacter ->
            characterCacheMapper.mapFromCached(cacheCharacter)
        }
    }

    override suspend fun setCharacterBookmarked(characterId: Long): Int {
        return characterDao.bookmarkCharacter(characterId)
    }

    override suspend fun setCharacterUnBookMarked(characterId: Long): Int {
        return characterDao.unBookmarkCharacter(characterId)
    }

    override suspend fun isCached(): Boolean {
        return characterDao.getCharacters().isNotEmpty()
    }

    override suspend fun setLastCacheTime(lastCache: Long) {
        preferencesHelper.lastCacheTime = lastCache
    }

    override suspend fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = getLastCacheUpdateTimeMillis()
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
