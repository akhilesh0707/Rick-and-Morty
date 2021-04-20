package com.aqube.ram.data.repository

import com.aqube.ram.data.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterCache {
    suspend fun getCharacters(): Flow<List<CharacterEntity>>
    suspend fun getCharacter(characterId: Long): Flow<CharacterEntity>
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>): Flow<Long>
    fun getBookMarkedCharacters(): Flow<List<CharacterEntity>>
    fun setCharacterBookmarked(characterId: Long): Flow<Long>
    fun setCharacterUnBookMarked(characterId: Long): Flow<Long>
    fun isCached(): Flow<Boolean>
    fun setLastCacheTime(lastCache: Long)
    fun isExpired(): Boolean
}
