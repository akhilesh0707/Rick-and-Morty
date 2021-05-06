package com.aqube.ram.cache.fakes

import com.aqube.ram.cache.fakes.FakeValueFactory.randomBoolean
import com.aqube.ram.cache.fakes.FakeValueFactory.randomInt
import com.aqube.ram.cache.fakes.FakeValueFactory.randomString
import com.aqube.ram.cache.models.CharacterCacheEntity
import com.aqube.ram.cache.models.CharacterLocationCacheEntity
import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterLocationEntity

object FakeCacheData {

    fun getFakeCharacterCacheEntity(size: Int): List<CharacterCacheEntity> {
        val listOfCachedMovie = mutableListOf<CharacterCacheEntity>()
        repeat(size) {
            listOfCachedMovie.add(createCharacterCacheEntity())
        }
        return listOfCachedMovie
    }

    fun getFakeCharacterEntity(size: Int): List<CharacterEntity> {
        val listOfMovieEntities = mutableListOf<CharacterEntity>()
        repeat(size) {
            listOfMovieEntities.add(createCharacterEntity())
        }
        return listOfMovieEntities
    }

    private fun createCharacterCacheEntity(): CharacterCacheEntity {
        return CharacterCacheEntity(
            created = randomString(),
            gender = randomString(),
            id = randomInt(),
            image = randomString(),
            characterLocation = CharacterLocationCacheEntity(
                name = randomString(),
                url = randomString()
            ),
            name = randomString(),
            species = randomString(),
            status = randomString(),
            randomString(),
            url = randomString(),
            isBookMarked = randomBoolean()
        )
    }

    private fun createCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            created = randomString(),
            gender = randomString(),
            id = randomInt(),
            image = randomString(),
            characterLocation = CharacterLocationEntity(
                name = randomString(),
                url = randomString()
            ),
            name = randomString(),
            species = randomString(),
            status = randomString(),
            randomString(),
            url = randomString(),
            isBookMarked = randomBoolean()
        )
    }
}