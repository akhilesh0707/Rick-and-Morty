package com.aqube.ram.data.source

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.repository.CharacterCache
import com.aqube.ram.data.repository.CharacterDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterCacheDataSource @Inject constructor(
    private val characterCache: CharacterCache
) : CharacterDataSource {

    override suspend fun getCharacters(): Flow<List<CharacterEntity>> {
        return characterCache.getCharacters()
    }

    override suspend fun getCharacter(characterId: Int): Flow<CharacterEntity> {
        return characterCache.getCharacter(characterId)
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        val count = characterCache.saveCharacters(listCharacters)
        characterCache.setLastCacheTime(System.currentTimeMillis())
        return count
    }

    override suspend fun getBookMarkedCharacters(): Flow<List<CharacterEntity>> {
        return characterCache.getBookMarkedCharacters()
    }

    override suspend fun setCharacterBookmarked(characterId: Int) {
        return characterCache.setCharacterBookmarked(characterId)
    }

    override suspend fun setCharacterUnBookMarked(characterId: Int) {
        return characterCache.setCharacterUnBookMarked(characterId)
    }

    override suspend fun isCached(): Flow<Boolean> {
        return characterCache.isCached()
    }
}