package com.aqube.ram.domain.repository

import com.aqube.ram.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    // Remote and cache
    suspend fun getCharacters(): Flow<List<Character>>
    suspend fun getCharacter(characterId: Long): Flow<Character>

    /*// Cache
    suspend fun saveCharacters(listCharacters: List<Character>): Flow<Long>
    suspend fun getBookMarkedCharacters(): Flow<Character>
    suspend fun setCharacterBookmarked(characterId: Int): Flow<Long>
    suspend fun setCharacterUnBookMarked(characterId: Int): Flow<Long>*/
}
