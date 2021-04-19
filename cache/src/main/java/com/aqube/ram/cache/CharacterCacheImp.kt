package com.aqube.ram.cache

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
            cacheList.map { cacheCharacter ->
                characterCacheMapper.mapFromCached(cacheCharacter)
            }.asFlow()
        }
    }

    override suspend fun getCharacter(characterId: Int): Flow<CharacterEntity> = flow {
        characterDao.getCharacter(characterId).collect { cacheCharacter ->
            emit(characterCacheMapper.mapFromCached(cacheCharacter))
        }
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        TODO("Not yet implemented")
    }

    override fun getBookMarkedCharacters(): Flow<List<CharacterEntity>> {
        TODO("Not yet implemented")
    }

    override fun setCharacterBookmarked(characterId: Int) {
        TODO("Not yet implemented")
    }

    override fun setCharacterUnBookMarked(characterId: Int) {
        TODO("Not yet implemented")
    }

    override fun isCached(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun setLastCacheTime(lastCache: Long) {
        TODO("Not yet implemented")
    }

    override fun isExpired(): Boolean {
        TODO("Not yet implemented")
    }

}