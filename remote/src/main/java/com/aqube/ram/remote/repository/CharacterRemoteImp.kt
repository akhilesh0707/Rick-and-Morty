package com.aqube.ram.remote.repository

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterListEntity
import com.aqube.ram.data.repository.CharacterRemote
import com.aqube.ram.remote.api.CharacterService
import com.aqube.ram.remote.mappers.CharacterEntityMapper
import com.aqube.ram.remote.mappers.CharacterListEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRemoteImp @Inject constructor(
    private val characterService: CharacterService,
    private val characterListEntityMapper: CharacterListEntityMapper,
    private val characterEntityMapper: CharacterEntityMapper
) : CharacterRemote {

    override suspend fun getCharacters(): Flow<CharacterListEntity> = flow {
        val characters = characterService.getCharacters()
        emit(characterListEntityMapper.mapFromModel(characters))
    }

    override suspend fun getCharacter(characterId: Int): Flow<CharacterEntity> = flow {
        val character = characterService.getCharacter(characterId)
        emit(characterEntityMapper.mapFromModel(character))
    }
}
