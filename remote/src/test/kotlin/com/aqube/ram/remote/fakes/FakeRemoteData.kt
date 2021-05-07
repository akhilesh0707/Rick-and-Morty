package com.aqube.ram.remote.fakes

import com.aqube.ram.remote.fakes.FakeValueFactory.randomInt
import com.aqube.ram.remote.fakes.FakeValueFactory.randomString
import com.aqube.ram.remote.models.CharacterLocationModel
import com.aqube.ram.remote.models.CharacterModel

object FakeRemoteData {

    fun getFakeCharacterModel(size: Int): List<CharacterModel> {
        val listOfMovieEntities = mutableListOf<CharacterModel>()
        repeat(size) {
            listOfMovieEntities.add(createCharacterModel())
        }
        return listOfMovieEntities
    }

    private fun createCharacterModel(): CharacterModel {
        return CharacterModel(
            created = randomString(),
            gender = randomString(),
            id = randomInt(),
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
}