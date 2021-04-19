package com.aqube.ram.cache.mapper

import com.aqube.ram.cache.models.CharacterLocationCache
import com.aqube.ram.data.models.CharacterLocationEntity
import javax.inject.Inject

class CharacterLocationCacheMapper @Inject constructor() :
    CacheMapper<CharacterLocationCache, CharacterLocationEntity> {
    override fun mapFromCached(type: CharacterLocationCache): CharacterLocationEntity {
        return CharacterLocationEntity(name = type.name, url = type.url)
    }

    override fun mapToCached(type: CharacterLocationEntity): CharacterLocationCache {
        return CharacterLocationCache(name = type.name, url = type.url)
    }
}