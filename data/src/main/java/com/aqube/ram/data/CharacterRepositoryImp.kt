package com.aqube.ram.data

import com.aqube.ram.data.mapper.CharacterListMapper
import com.aqube.ram.data.mapper.CharacterMapper
import com.aqube.ram.data.source.CharacterDataSourceFactory
import com.aqube.ram.domain.models.CharacterList
import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val dataSourceFactory: CharacterDataSourceFactory,
    private val characterListMapper: CharacterListMapper,
    private val characterMapper: CharacterMapper,
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<CharacterList> = flow {
        dataSourceFactory.getRemoteDataSource().getCharacters().collect {
            emit(characterListMapper.mapFromEntity(it))
        }
    }

    override suspend fun getCharacter(characterId: Int): Flow<Character> = flow {
        dataSourceFactory.getRemoteDataSource().getCharacter(characterId).collect {
            emit(characterMapper.mapFromEntity(it))
        }
    }
}
