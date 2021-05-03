package com.aqube.ram.domain.fakes

import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.models.CharacterLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getCharacters(): Flow<List<Character>> = flow {
        val characters = listOf(
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
            ),
            Character(
                "01/02/2021",
                "Male",
                2,
                "https://dummyurl.png",
                CharacterLocation("Earth", "https://dummy.url"),
                "Morty",
                "Human",
                "Alive",
                "",
                "",
                false
            )
        )
        emit(characters)
    }

    fun getCharacter(): Flow<Character> = flow {
        emit(
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
        )
    }
}