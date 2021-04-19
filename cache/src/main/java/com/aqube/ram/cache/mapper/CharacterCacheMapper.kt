package com.aqube.ram.cache.mapper

import com.aqube.ram.cache.models.CharacterCache
import com.aqube.ram.data.models.CharacterEntity
import javax.inject.Inject

class CharacterCacheMapper @Inject constructor(
    private val characterLocationCacheMapper: CharacterLocationCacheMapper
) : CacheMapper<CharacterCache, CharacterEntity> {
    override fun mapFromCached(type: CharacterCache): CharacterEntity {
        return CharacterEntity(
            created = type.created,
            gender = type.gender,
            id = type.id,
            image = type.image,
            characterLocation = characterLocationCacheMapper.mapFromCached(type.characterLocation),
            name = type.name,
            species = type.species,
            status = type.status,
            type = type.type,
            url = type.url,
            isBookMarked = type.isBookMarked
        )
    }

    override fun mapToCached(type: CharacterEntity): CharacterCache {
        return CharacterCache(
            created = type.created,
            gender = type.gender,
            id = type.id,
            image = type.image,
            characterLocation = characterLocationCacheMapper.mapToCached(type.characterLocation),
            name = type.name,
            species = type.species,
            status = type.status,
            type = type.type,
            url = type.url,
            isBookMarked = type.isBookMarked
        )
    }
}