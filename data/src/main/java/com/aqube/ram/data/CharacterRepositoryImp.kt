package com.aqube.ram.data

import com.aqube.ram.data.mapper.CharacterMapper
import com.aqube.ram.data.source.CharacterDataSourceFactory
import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val dataSourceFactory: CharacterDataSourceFactory,
    private val characterMapper: CharacterMapper,
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<Character>> = flow {
        val isCached = dataSourceFactory.getCacheDataSource().isCached()
        val characterList =
            dataSourceFactory.getDataStore(isCached).getCharacters().map { characterEntity ->
                characterMapper.mapFromEntity(characterEntity)
            }
        saveCharacters(characterList)
        emit(characterList)
    }

    override suspend fun getCharacter(characterId: Long): Flow<Character> = flow {
        var character = dataSourceFactory.getCacheDataSource().getCharacter(characterId)
        if (character.image.isEmpty()) {
            character = dataSourceFactory.getRemoteDataSource().getCharacter(characterId)
        }
        emit(
            characterMapper.mapFromEntity(character)
        )
    }

    override suspend fun saveCharacters(listCharacters: List<Character>) {
        val characterEntities = listCharacters.map { character ->
            characterMapper.mapToEntity(character)
        }
        dataSourceFactory.getCacheDataSource().saveCharacters(characterEntities)
    }

    override suspend fun getBookMarkedCharacters(): Flow<List<Character>> = flow {
        val characterList = dataSourceFactory.getCacheDataSource().getBookMarkedCharacters()
            .map { characterEntity ->
                characterMapper.mapFromEntity(characterEntity)
            }
        emit(characterList)
    }

    override suspend fun setCharacterBookmarked(characterId: Long): Flow<Int> = flow {
        emit(dataSourceFactory.getCacheDataSource().setCharacterBookmarked(characterId))
    }

    override suspend fun setCharacterUnBookMarked(characterId: Long): Flow<Int> = flow {
        emit(dataSourceFactory.getCacheDataSource().setCharacterUnBookMarked(characterId))
    }
}
