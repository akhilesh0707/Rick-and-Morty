package com.aqube.ram.data.mapper

import com.aqube.ram.data.models.CharacterListEntity
import com.aqube.ram.domain.models.CharacterList
import javax.inject.Inject

class CharacterListMapper @Inject constructor(
    private val infoMapper: InfoMapper,
    private val characterMapper: CharacterMapper
) :
    Mapper<CharacterListEntity, CharacterList> {

    override fun mapFromEntity(type: CharacterListEntity): CharacterList {
        return CharacterList(
            info = infoMapper.mapFromEntity(type.info),
            characters = type.characters.map { characterMapper.mapFromEntity(it) }
        )
    }

    override fun mapToEntity(type: CharacterList): CharacterListEntity {
        return CharacterListEntity(
            info = infoMapper.mapToEntity(type.info),
            characters = type.characters.map { characterMapper.mapToEntity(it) }
        )
    }
}
