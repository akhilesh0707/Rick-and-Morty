package com.aqube.ram.remote.mappers

import com.aqube.ram.data.models.CharacterListEntity
import com.aqube.ram.remote.models.CharacterListModel
import javax.inject.Inject

class CharacterListEntityMapper @Inject constructor(
    private val characterEntityMapper: CharacterEntityMapper,
    private val infoEntityMapper: InfoEntityMapper
) : EntityMapper<CharacterListModel, CharacterListEntity> {
    override fun mapFromModel(model: CharacterListModel): CharacterListEntity {
        return CharacterListEntity(info = infoEntityMapper.mapFromModel(model.info),
            characters = model.characters.map { characterEntityMapper.mapFromModel(it) }
        )
    }
}
