package com.aqube.ram.domain.interactor

import com.aqube.ram.domain.fakes.FakeData
import com.aqube.ram.domain.repository.CharacterRepository
import com.aqube.ram.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterByIdTestDomain : DomainBaseTest() {

    @Mock
    lateinit var characterRepository: CharacterRepository

    lateinit var sut: GetCharacterByIdUseCase

    @Before
    fun setUp() {
        sut = GetCharacterByIdUseCase(characterRepository)
    }

    @Test
    fun `get character with id should return success result with character detail`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            whenever(characterRepository.getCharacter(characterId)) doReturn FakeData.getCharacter()

            // Act (When)
            val character = sut(characterId).single()

            // Assert (Then)
            assertEquals(character.id, characterId.toInt())
            assertEquals(character.name, "Rick")
            verify(characterRepository, times(1)).getCharacter(characterId)
        }

    @Test
    fun `get character with id should return error result with exception`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            whenever(characterRepository.getCharacter(characterId)) doAnswer { throw IOException() }

            // Act (When)
            launch(exceptionHandler) { sut(characterId).single() }

            // Assert (Then)
            MatcherAssert.assertThat(
                exceptionHandler.uncaughtExceptions.first(),
                instanceOf(IOException::class.java)
            )
            verify(characterRepository, times(1)).getCharacter(characterId)
        }
}
