package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.repository.CharacterRepository
import com.aqube.ram.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class CharacterUnBookmarkTestDomain : DomainBaseTest() {
    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var sut: CharacterUnBookmarkUseCase

    @Before
    fun setUp() {
        sut = CharacterUnBookmarkUseCase(characterRepository)
    }

    @Test
    fun `set unbookmark character with id should return success with response code`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            whenever(characterRepository.setCharacterUnBookMarked(characterId)) doReturn flow {
                emit(
                    1
                )
            }

            // Act (When)
            val status = sut(characterId).single()

            // Assert (Then)
            assertEquals(status, 1)
            verify(characterRepository, times(1)).setCharacterUnBookMarked(characterId)
        }

    @Test
    fun `set unbookmark character with id should return fail with response code`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            whenever(characterRepository.setCharacterUnBookMarked(characterId)) doReturn flow {
                emit(
                    0
                )
            }

            // Act (When)
            val status = sut(characterId).single()

            // Assert (Then)
            assertEquals(status, 0)
            verify(characterRepository, times(1)).setCharacterUnBookMarked(characterId)
        }

    @Test
    fun `set unbookmark character with id should return error result with exception`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            whenever(characterRepository.setCharacterUnBookMarked(characterId)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut(characterId).single() }

            // Assert (Then)
            assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(IOException::class.java)
            )
            verify(characterRepository, times(1)).setCharacterUnBookMarked(characterId)
        }
}
