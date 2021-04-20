package com.aqube.ram.data

import com.aqube.ram.data.mapper.CharacterMapper
import com.aqube.ram.data.source.CharacterDataSourceFactory
import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val dataSourceFactory: CharacterDataSourceFactory,
    private val characterMapper: CharacterMapper,
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<Character>> = flow {
        dataSourceFactory.getRemoteDataSource().getCharacters().collect { listEntity ->
            emit(
                listEntity.map { character -> characterMapper.mapFromEntity(character) }
            )
        }
    }

    override suspend fun getCharacter(characterId: Long): Flow<Character> = flow {
        dataSourceFactory.getRemoteDataSource().getCharacter(characterId).collect {
            emit(characterMapper.mapFromEntity(it))
        }
    }
}
