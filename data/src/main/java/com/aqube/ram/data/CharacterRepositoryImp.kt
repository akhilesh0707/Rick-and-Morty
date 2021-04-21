package com.aqube.ram.data

import com.aqube.ram.data.mapper.CharacterMapper
import com.aqube.ram.data.source.CharacterDataSourceFactory
import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.*

import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val dataSourceFactory: CharacterDataSourceFactory,
    private val characterMapper: CharacterMapper,
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<Character>> = flow {
        val isCached = dataSourceFactory.getCacheDataSource().isCached()
        dataSourceFactory.getDataStore(isCached).getCharacters().collect { characterEntities ->
            val characterList: List<Character> = characterEntities.map { characterEntity ->
                characterMapper.mapFromEntity(characterEntity)
            }
            saveCharacters(characterList)
            emit(characterList)
        }

        /*dataSourceFactory.getRemoteDataSource().getCharacters().collect {
            emit(
                it.map {
                    characterMapper.mapFromEntity(it)
                })
        }*/
    }

    override suspend fun getCharacter(characterId: Long): Flow<Character> = flow {
        dataSourceFactory.getRemoteDataSource().getCharacter(characterId).collect {
            emit(characterMapper.mapFromEntity(it))
        }
    }

    override suspend fun saveCharacters(listCharacters: List<Character>) {
        val characterEntities =listCharacters.map { character ->
            characterMapper.mapToEntity(character)
        }
        dataSourceFactory.getCacheDataSource().saveCharacters(characterEntities)
    }

    override suspend fun getBookMarkedCharacters(): Flow<List<Character>> = flow {
        dataSourceFactory.getCacheDataSource().getBookMarkedCharacters()
            .collect { listCharacterEntity ->
                listCharacterEntity.map { characterEntity ->
                    characterMapper.mapFromEntity(characterEntity)
                }.asFlow()
            }
    }

    override suspend fun setCharacterBookmarked(characterId: Long): Flow<Int> {
        return dataSourceFactory.getCacheDataSource().setCharacterBookmarked(characterId)
    }

    override suspend fun setCharacterUnBookMarked(characterId: Long): Flow<Int> {
        return dataSourceFactory.getCacheDataSource().setCharacterUnBookMarked(characterId)
    }
}
