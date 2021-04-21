package com.aqube.ram.remote.repository

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.repository.CharacterRemote
import com.aqube.ram.remote.api.CharacterService
import com.aqube.ram.remote.mappers.CharacterEntityMapper
import javax.inject.Inject

class CharacterRemoteImp @Inject constructor(
    private val characterService: CharacterService,
    private val characterEntityMapper: CharacterEntityMapper
) : CharacterRemote {

    override suspend fun getCharacters(): List<CharacterEntity> {
        return characterService.getCharacters().characters.map { characterModel ->
            characterEntityMapper.mapFromModel(characterModel)
        }
    }

    override suspend fun getCharacter(characterId: Long): CharacterEntity {
        return characterEntityMapper.mapFromModel(characterService.getCharacter(characterId))
    }
}
