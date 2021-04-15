package com.aqube.ram.remote.mappers

import com.aqube.ram.data.models.CharacterLocationEntity
import com.aqube.ram.remote.models.CharacterLocationModel
import javax.inject.Inject

class CharacterLocationEntityMapper @Inject constructor() :
    EntityMapper<CharacterLocationModel, CharacterLocationEntity> {
    override fun mapFromModel(model: CharacterLocationModel): CharacterLocationEntity {
        return CharacterLocationEntity(name = model.name, url = model.url)
    }
}
