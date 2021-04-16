package com.aqube.ram.remote.mappers

import com.aqube.ram.data.models.InfoEntity
import com.aqube.ram.remote.models.InfoModel
import javax.inject.Inject

class InfoEntityMapper @Inject constructor() : EntityMapper<InfoModel, InfoEntity> {
    override fun mapFromModel(model: InfoModel): InfoEntity {
        return InfoEntity(
            count = model.count,
            next = model.next,
            pages = model.pages,
        )
    }
}
