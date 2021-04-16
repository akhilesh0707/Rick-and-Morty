package com.aqube.ram.data.source

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterListEntity
import com.aqube.ram.data.repository.CharacterDataSource
import com.aqube.ram.data.repository.CharacterRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterRemote: CharacterRemote
) : CharacterDataSource {

    override suspend fun getCharacters(): Flow<CharacterListEntity> {
        return characterRemote.getCharacters()
    }

    override suspend fun getCharacter(characterId: Int): Flow<CharacterEntity> {
        return characterRemote.getCharacter(characterId)
    }

}
