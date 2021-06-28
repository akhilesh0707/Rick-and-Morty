package com.aqube.ram.data.mapper

import com.aqube.ram.data.fakes.FakeCharacters
import com.aqube.ram.data.models.CharacterLocationEntity
import com.aqube.ram.data.utils.DataBaseTest
import com.aqube.ram.domain.models.CharacterLocation
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterMapperTest : DataBaseTest() {

    @Mock
    lateinit var locationMapper: CharacterLocationMapper

    lateinit var sut: CharacterMapper

    @Before
    fun setUp() {
        sut = CharacterMapper(locationMapper)
    }

    @Test
    fun `map  character entity to character should return converted character`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterFake = FakeCharacters.getCharacters()[0]
            `when`(locationMapper.mapFromEntity(characterFake.characterLocation)) doReturn CharacterLocation(
                "",
                ""
            )
            // Act (When)
            val character = sut.mapFromEntity(characterFake)

            // Assert (Then)
            assertEquals(character.id, 1)
            assertEquals(character.name, "Rick")
            assertEquals(character.gender, "Male")
            verify(locationMapper, times(1)).mapFromEntity(any())
        }

    @Test
    fun `map character to character entity  should return converted character`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterFake = FakeCharacters.getCharacter()
            `when`(locationMapper.mapToEntity(characterFake.characterLocation)) doReturn CharacterLocationEntity(
                "",
                ""
            )
            // Act (When)
            val character = sut.mapToEntity(characterFake)

            // Assert (Then)
            assertEquals(character.id, 1)
            assertEquals(character.name, "Rick")
            assertEquals(character.gender, "Male")
            verify(locationMapper, times(1)).mapToEntity(any())
        }
}
