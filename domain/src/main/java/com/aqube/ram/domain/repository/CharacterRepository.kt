package com.aqube.ram.domain.repository

import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.models.CharacterList
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    // Remote and cache
    suspend fun getCharacters(): Flow<CharacterList>
    suspend fun getCharacter(characterId: Int): Flow<Character>

   /* // Cache
    suspend fun saveCharacters(listCharacters: List<CharacterList>)
    suspend fun getBookMarkedCharacters(): Flow<CharacterList>
    suspend fun setCharacterBookmarked(characterId: Int)
    suspend fun setCharacterUnBookMarked(characterId: Int)*/
}
