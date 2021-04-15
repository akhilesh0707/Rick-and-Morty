package com.aqube.ram.data.repository

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterListEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRemote {
    suspend fun getCharacters(): Flow<CharacterListEntity>
    suspend fun getCharacter(characterId: Int): Flow<CharacterEntity>
}