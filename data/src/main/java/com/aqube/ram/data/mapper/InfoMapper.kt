package com.aqube.ram.data.mapper

import com.aqube.ram.data.models.CharacterLocationEntity
import com.aqube.ram.data.models.InfoEntity
import com.aqube.ram.domain.models.CharacterLocation
import com.aqube.ram.domain.models.Info
import javax.inject.Inject

class InfoMapper @Inject constructor() : Mapper<InfoEntity, Info> {
    override fun mapFromEntity(type: InfoEntity): Info {
        return Info(
            count = type.count,
            next = type.next,
            pages = type.pages,
            prev = type.prev
        )
    }

    override fun mapToEntity(type: Info): InfoEntity {
        return InfoEntity(
            count = type.count,
            next = type.next,
            pages = type.pages,
            prev = type.prev
        )
    }
}
