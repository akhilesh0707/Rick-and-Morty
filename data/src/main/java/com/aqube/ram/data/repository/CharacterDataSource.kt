package com.aqube.ram.data.repository

import com.aqube.ram.data.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterDataSource {
    // Remote and cache
    suspend fun getCharacters(): Flow<List<CharacterEntity>>
    suspend fun getCharacter(characterId: Long): Flow<CharacterEntity>

    // Cache
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>): Flow<Long>
    suspend fun getBookMarkedCharacters(): Flow<List<CharacterEntity>>
    suspend fun setCharacterBookmarked(characterId: Long): Flow<Long>
    suspend fun setCharacterUnBookMarked(characterId: Long): Flow<Long>
    suspend fun isCached(): Flow<Boolean>
}
