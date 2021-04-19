package com.aqube.ram.data.repository

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterListEntity
import kotlinx.coroutines.flow.Flow

interface CharacterDataSource {
    // Remote and cache
    suspend fun getCharacters(): Flow<CharacterListEntity>
    suspend fun getCharacter(characterId: Int): Flow<CharacterEntity>

    // Cache
    suspend fun saveCharacters(listCharacters: List<CharacterEntity>)
    suspend fun getBookMarkedCharacters(): Flow<CharacterListEntity>
    suspend fun setCharacterBookmarked(characterId: Int)
    suspend fun setCharacterUnBookMarked(characterId: Int)
    suspend fun isCached(): Flow<Boolean>
}
