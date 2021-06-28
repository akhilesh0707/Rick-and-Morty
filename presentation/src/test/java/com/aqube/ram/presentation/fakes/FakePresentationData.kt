package com.aqube.ram.presentation.fakes

import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.models.CharacterLocation
import com.aqube.ram.domain.models.SettingType
import com.aqube.ram.domain.models.Settings
import com.aqube.ram.presentation.fakes.FakeValueFactory.randomBoolean
import com.aqube.ram.presentation.fakes.FakeValueFactory.randomInt
import com.aqube.ram.presentation.fakes.FakeValueFactory.randomString

object FakePresentationData {

    fun getCharacters(
        size: Int,
        isRandomId: Boolean = true,
        isBookmarked: Boolean = false
    ): List<Character> {
        val characters = mutableListOf<Character>()
        repeat(size) {
            characters.add(createCharacter(isRandomId, isBookmarked))
        }
        return characters
    }

    fun getSettings(size: Int): List<Settings> {
        val settings = mutableListOf<Settings>()
        repeat(size) {
            settings.add(createSetting())
        }
        return settings
    }

    private fun createCharacter(isRandomId: Boolean, isBookmarked: Boolean): Character {
        return Character(
            created = randomString(),
            gender = randomString(),
            id = if (isRandomId) randomInt() else 1,
            image = randomString(),
            characterLocation = CharacterLocation(
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

    private fun createSetting(): Settings {
        return Settings(
            id = randomInt(),
            type = SettingType.SWITCH,
            settingLabel = randomString(),
            settingValue = randomString()
        )
    }
}
