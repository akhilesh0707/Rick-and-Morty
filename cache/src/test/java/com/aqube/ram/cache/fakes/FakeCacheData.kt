package com.aqube.ram.cache.fakes

import com.aqube.ram.cache.fakes.FakeValueFactory.randomBoolean
import com.aqube.ram.cache.fakes.FakeValueFactory.randomInt
import com.aqube.ram.cache.fakes.FakeValueFactory.randomString
import com.aqube.ram.cache.models.CharacterCacheEntity
import com.aqube.ram.cache.models.CharacterLocationCacheEntity
import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterLocationEntity

object FakeCacheData {

    fun getFakeCharacterEntity(
        size: Int,
        isRandomId: Boolean = true,
        isBookmarked: Boolean = false
    ): List<CharacterEntity> {
        val listOfMovieEntities = mutableListOf<CharacterEntity>()
        repeat(size) {
            listOfMovieEntities.add(createCharacterEntity(isRandomId, isBookmarked))
        }
        return listOfMovieEntities
    }

    private fun createCharacterEntity(isRandomId: Boolean, isBookmarked: Boolean): CharacterEntity {
        return CharacterEntity(
            created = randomString(),
            gender = randomString(),
            id = if (isRandomId) randomInt() else 1,
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
            isBookMarked = if (isBookmarked) true else randomBoolean()
        )
    }
}