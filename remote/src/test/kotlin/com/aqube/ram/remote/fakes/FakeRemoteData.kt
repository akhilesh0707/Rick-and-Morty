package com.aqube.ram.remote.fakes

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterLocationEntity
import com.aqube.ram.remote.fakes.FakeValueFactory.randomInt
import com.aqube.ram.remote.fakes.FakeValueFactory.randomString
import com.aqube.ram.remote.models.CharacterLocationModel
import com.aqube.ram.remote.models.CharacterModel
import com.aqube.ram.remote.models.CharacterResponseModel

object FakeRemoteData {

    fun getResponse(size: Int, isRandomId: Boolean = true): CharacterResponseModel {
        return CharacterResponseModel(getFakeCharacterModel(size, isRandomId))
    }

    private fun getFakeCharacterModel(size: Int, isRandomId: Boolean): List<CharacterModel> {
        val characters = mutableListOf<CharacterModel>()
        repeat(size) {
            characters.add(getCharacterModel(isRandomId))
        }
        return characters
    }

    fun getCharacterModel(isRandomId: Boolean): CharacterModel {
        return CharacterModel(
            created = randomString(),
            gender = randomString(),
            id = if (isRandomId) randomInt() else 1,
            image = randomString(),
            characterLocation = CharacterLocationModel(
                name = randomString(),
                url = randomString()
            ),
            name = randomString(),
            species = randomString(),
            status = randomString(),
            type = randomString(),
            url = randomString(),
            isBookMarked = false
        )
    }

    fun getCharacterEntity(isRandomId: Boolean): CharacterEntity {
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
            type = randomString(),
            url = randomString(),
            isBookMarked = false
        )
    }
}
