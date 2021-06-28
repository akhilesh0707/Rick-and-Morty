package com.aqube.ram.remote.repository

import com.aqube.ram.remote.api.CharacterService
import com.aqube.ram.remote.fakes.FakeRemoteData
import com.aqube.ram.remote.mappers.CharacterEntityMapper
import com.aqube.ram.remote.utils.RemoteBaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterRemoteImpTest : RemoteBaseTest() {

    @Mock
    lateinit var characterService: CharacterService

    @Mock
    lateinit var mapper: CharacterEntityMapper

    lateinit var sut: CharacterRemoteImp

    @Before
    fun setUp() {
        sut = CharacterRemoteImp(characterService, mapper)
    }

    @Test
    fun `get characters should return response with list size 7 from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = FakeRemoteData.getResponse(7)
            `when`(characterService.getCharacters()) doReturn response

            // Act (When)
            val characters = sut.getCharacters()

            // Assert (Then)
            assertEquals(characters.size, 7)
            verify(mapper, times(7)).mapFromModel(any())
        }

    @Test
    fun `get characters should return response with empty character list from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val response = FakeRemoteData.getResponse(0)
            `when`(characterService.getCharacters()) doReturn response

            // Act (When)
            val characters = sut.getCharacters()

            // Assert (Then)
            assertEquals(characters.size, 0)
            verify(mapper, times(0)).mapFromModel(any())
        }

    @Test
    fun `get characters should return error from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(characterService.getCharacters()) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getCharacters() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(characterService, times(1)).getCharacters()
        }

    @Test
    fun `get character should return response from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            val response = FakeRemoteData.getCharacterModel(false)
            `when`(characterService.getCharacter(characterId)) doReturn response
            `when`(mapper.mapFromModel(response)) doReturn FakeRemoteData.getCharacterEntity(false)
            // Act (When)
            val character = sut.getCharacter(characterId)

            // Assert (Then)
            assertEquals(character.id, 1)
            assertTrue(character.name.isNotEmpty())
            verify(characterService, times(1)).getCharacter(characterId)
            verify(mapper, times(1)).mapFromModel(any())
        }

    @Test
    fun `get character should return error response from remote server`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            whenever(characterService.getCharacter(characterId)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getCharacter(characterId) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(characterService, times(1)).getCharacter(characterId)
        }
}
