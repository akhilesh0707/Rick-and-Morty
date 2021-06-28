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
import kotlinx.coroutines.InternalCoroutinesApi
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
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterListTestDomain : DomainBaseTest() {

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
            whenever(characterRepository.getCharacters()) doReturn FakeData.getCharacters()

            // Act (When)
            val characters = sut(Unit).single()

            // Assert (Then)
            assertEquals(characters.size, 2)
            verify(characterRepository, times(1)).getCharacters()
        }

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
}
