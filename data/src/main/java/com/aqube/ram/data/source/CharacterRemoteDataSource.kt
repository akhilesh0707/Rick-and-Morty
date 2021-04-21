package com.aqube.ram.data.source

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.repository.CharacterDataSource
import com.aqube.ram.data.repository.CharacterRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterRemote: CharacterRemote
) : CharacterDataSource {

    override suspend fun getCharacters(): Flow<List<CharacterEntity>> {
        return characterRemote.getCharacters()
    }

    override suspend fun getCharacter(characterId: Long): Flow<CharacterEntity> {
        return characterRemote.getCharacter(characterId)
    }

    override suspend fun saveCharacters(listCharacters: List<CharacterEntity>) {
        throw UnsupportedOperationException("Save character is not supported for RemoteDataSource.")
    }

    override suspend fun getBookMarkedCharacters(): Flow<List<CharacterEntity>> {
        throw UnsupportedOperationException("Get bookmark characters is not supported for RemoteDataSource.")
    }

    override suspend fun setCharacterBookmarked(characterId: Long): Flow<Int> {
        throw UnsupportedOperationException("Set bookmark character is not supported for RemoteDataSource.")
    }

    override suspend fun setCharacterUnBookMarked(characterId: Long): Flow<Int> {
        throw UnsupportedOperationException("Set UnBookmark characters is not supported for RemoteDataSource.")
    }

    override suspend fun isCached(): Boolean {
        throw UnsupportedOperationException("Cache is not supported for RemoteDataSource.")
    }
}
