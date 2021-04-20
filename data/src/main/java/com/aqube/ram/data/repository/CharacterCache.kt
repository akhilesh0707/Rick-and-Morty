package com.aqube.ram.data.repository

import com.aqube.ram.data.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterCache {
    suspend fun getCharacters(): Flow<List<CharacterEntity>>
    suspend fun getCharacter(characterId: Long): Flow<CharacterEntity>
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)
    suspend fun getBookMarkedCharacters(): Flow<List<CharacterEntity>>
    suspend fun setCharacterBookmarked(characterId: Long): Flow<Int>
    suspend fun setCharacterUnBookMarked(characterId: Long): Flow<Int>
    fun isCached(): Boolean
    suspend fun setLastCacheTime(lastCache: Long)
    suspend fun isExpired(): Boolean
}
