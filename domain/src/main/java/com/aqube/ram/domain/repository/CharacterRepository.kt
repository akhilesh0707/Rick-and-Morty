package com.aqube.ram.domain.repository

import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.models.CharacterList
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacters(): Flow<CharacterList>
    suspend fun getCharacter(characterId: Int): Flow<Character>
}
