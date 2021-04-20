package com.aqube.ram.remote.repository

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.repository.CharacterRemote
import com.aqube.ram.remote.api.CharacterService
import com.aqube.ram.remote.mappers.CharacterEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRemoteImp @Inject constructor(
    private val characterService: CharacterService,
    private val characterEntityMapper: CharacterEntityMapper
) : CharacterRemote {

    override suspend fun getCharacters(): Flow<List<CharacterEntity>> = flow {
        val characters = characterService.getCharacters()
        emit(characters.characters.map { characterEntityMapper.mapFromModel(it) })
    }

    override suspend fun getCharacter(characterId: Long): Flow<CharacterEntity> = flow {
        val character = characterService.getCharacter(characterId)
        emit(characterEntityMapper.mapFromModel(character))
    }
}
