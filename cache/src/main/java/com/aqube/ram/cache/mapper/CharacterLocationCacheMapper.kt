package com.aqube.ram.cache.mapper

import com.aqube.ram.cache.models.CharacterLocationCacheEntity
import com.aqube.ram.data.models.CharacterLocationEntity
import javax.inject.Inject

class CharacterLocationCacheMapper @Inject constructor() :
    CacheMapper<CharacterLocationCacheEntity, CharacterLocationEntity> {
    override fun mapFromCached(type: CharacterLocationCacheEntity): CharacterLocationEntity {
        return CharacterLocationEntity(name = type.name, url = type.url)
    }

    override fun mapToCached(type: CharacterLocationEntity): CharacterLocationCacheEntity {
        return CharacterLocationCacheEntity(name = type.name, url = type.url)
    }
}
