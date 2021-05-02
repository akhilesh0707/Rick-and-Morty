package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.repository.CharacterRepository
import com.aqube.ram.domain.models.Character
import com.aqube.ram.domain.utils.BaseUseCaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterListUseCaseTest : BaseUseCaseTest() {

    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var sut: GetCharacterListUseCase

    @Before
    fun setUp() {
        sut = GetCharacterListUseCase(characterRepository)
    }

    @Test
    fun `get character should return success result with character list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            whenever(characterRepository.getCharacters()) doReturn getCharacters()

            // Act (When)
            val characters = sut(Unit).single()

            // Assert (Then)
            assertEquals(characters.size, 3)
            verify(characterRepository, times(1)).getCharacters()
        }

    @InternalCoroutinesApi
    @Test
    fun `get characters should return error result with exception`() = dispatcher.runBlockingTest {
        // Arrange (Given)
        whenever(characterRepository.getCharacters()) doAnswer { throw IOException() }

        // Act (When)
        launch(exceptionHandler) { sut(Unit).single() }

        // Assert (Then)
        assertThat(exceptionHandler.uncaughtExceptions.first(), instanceOf(IOException::class.java))
        verify(characterRepository, times(1)).getCharacters()
    }

    private fun getCharacters(): Flow<List<Character>> = flow {
        val characters = listOf<Character>(
            mock { Character::class.java },
            mock { Character::class.java },
            mock { Character::class.java }
        )
        emit(characters)
    }
}