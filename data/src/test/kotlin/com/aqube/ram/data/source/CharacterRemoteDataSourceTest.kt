package com.aqube.ram.data.source

import com.aqube.ram.data.fakes.FakeCharacters
import com.aqube.ram.data.repository.CharacterRemote
import com.aqube.ram.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterRemoteDataSourceTest : DataBaseTest() {

    @Mock
    lateinit var characterRemote: CharacterRemote

    lateinit var sut: CharacterRemoteDataSource

    @Before
    fun setUp() {
        sut = CharacterRemoteDataSource(characterRemote)
    }

    @Test
    fun `get characters should return characters from remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            Mockito.`when`(characterRemote.getCharacters()) doReturn FakeCharacters.getCharacters()

            // Act (When)
            val characters = sut.getCharacters()

            // Assert (Then)
            TestCase.assertEquals(characters.size, 2)
            verify(characterRemote, times(1)).getCharacters()
        }

    @Test
    fun `get characters should return error`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(characterRemote.getCharacters()) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getCharacters() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(characterRemote, times(1)).getCharacters()
        }

    @Test
    fun `get character with character-id should return characters from remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            `when`(characterRemote.getCharacter(characterId)) doReturn FakeCharacters.getCharacters()[0]

            // Act (When)
            val characters = sut.getCharacter(characterId)

            // Assert (Then)
            assertEquals(characters.name, "Rick")
            verify(characterRemote, times(1)).getCharacter(characterId)
        }

    @Test
    fun `get character with character-id should return error`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            whenever(characterRemote.getCharacter(characterId)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut.getCharacter(characterId) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java)
            )
            verify(characterRemote, times(1)).getCharacter(characterId)
        }

    @Test
    fun `save characters should return error - not supported by remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            launch(exceptionHandler) { sut.saveCharacters(FakeCharacters.getCharacters()) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }

    @Test
    fun `get bookmark characters should return error - not supported by remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            launch(exceptionHandler) { sut.getBookMarkedCharacters() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }

    @Test
    fun `set bookmark character should return error - not supported by remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            launch(exceptionHandler) { sut.setCharacterBookmarked(1L) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }

    @Test
    fun `set un-bookmark character should return error - not supported by remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            launch(exceptionHandler) { sut.setCharacterUnBookMarked(1L) }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }

    @Test
    fun `is cached should return error - not supported by remote`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            launch(exceptionHandler) { sut.isCached() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(UnsupportedOperationException::class.java)
            )
        }
}
