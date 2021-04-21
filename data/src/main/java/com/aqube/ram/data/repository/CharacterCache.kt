package com.aqube.ram.data.repository

import com.aqube.ram.data.models.CharacterEntity

interface CharacterCache {
    suspend fun getCharacters(): List<CharacterEntity>
    suspend fun getCharacter(characterId: Long): CharacterEntity
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)
    suspend fun getBookMarkedCharacters(): List<CharacterEntity>
    suspend fun setCharacterBookmarked(characterId: Long): Int
    suspend fun setCharacterUnBookMarked(characterId: Long): Int
    suspend fun isCached(): Boolean
    suspend fun setLastCacheTime(lastCache: Long)
    suspend fun isExpired(): Boolean
}
