package com.aqube.ram.data.repository

import com.aqube.ram.data.models.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRemote {
    suspend fun getCharacters(): Flow<List<CharacterEntity>>
    suspend fun getCharacter(characterId: Int): Flow<CharacterEntity>
}