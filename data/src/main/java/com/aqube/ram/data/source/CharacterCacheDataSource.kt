package com.aqube.ram.data.source

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.repository.CharacterCache
import com.aqube.ram.data.repository.CharacterDataSource
import javax.inject.Inject

class CharacterCacheDataSource @Inject constructor(
    private val characterCache: CharacterCache
) : CharacterDataSource {

    override suspend fun getCharacters(): List<CharacterEntity> {
        return characterCache.getCharacters()
    }

    override suspend fun getCharacter(characterId: Long): CharacterEntity {
        return characterCache.getCharacter(characterId)
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        characterCache.saveCharacters(listCharacters)
        characterCache.setLastCacheTime(System.currentTimeMillis())
    }

    override suspend fun getBookMarkedCharacters(): List<CharacterEntity> {
        return characterCache.getBookMarkedCharacters()
    }

    override suspend fun setCharacterBookmarked(characterId: Long): Int {
        return characterCache.setCharacterBookmarked(characterId)
    }

    override suspend fun setCharacterUnBookMarked(characterId: Long): Int {
        return characterCache.setCharacterUnBookMarked(characterId)
    }

    override suspend fun isCached(): Boolean {
        return characterCache.isCached()
    }
}
