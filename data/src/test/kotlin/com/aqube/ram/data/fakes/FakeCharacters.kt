package com.aqube.ram.data.fakes

import com.aqube.ram.data.models.CharacterEntity
import com.aqube.ram.data.models.CharacterLocationEntity
import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.models.CharacterLocation

object FakeCharacters {
    fun getCharacters(): List<CharacterEntity> = listOf(
        CharacterEntity(
            "01/02/2021",
            "Male",
            1,
            "https://dummyurl.png",
            CharacterLocationEntity("Earth", "https://dummy.url"),
            "Rick",
            "Human",
            "Alive",
            "",
            "",
            false
        ),
        CharacterEntity(
            "01/02/2021",
            "Male",
            2,
            "https://dummyurl.png",
            CharacterLocationEntity("Earth", "https://dummy.url"),
            "Morty",
            "Human",
            "Alive",
            "",
            "",
            false
        )
    )

    fun getCharacter(): Character =
        Character(
            "01/02/2021",
            "Male",
            1,
            "https://dummyurl.png",
            CharacterLocation("Earth", "https://dummy.url"),
            "Rick",
            "Human",
            "Alive",
            "",
            "",
            false
        )
}
