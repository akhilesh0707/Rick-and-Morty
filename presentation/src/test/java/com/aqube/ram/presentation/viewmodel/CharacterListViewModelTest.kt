package com.aqube.ram.presentation.viewmodel

import androidx.lifecycle.Observer
import com.aqube.ram.domain.interactor.GetBookmarkCharacterListUseCase
import com.aqube.ram.domain.interactor.GetCharacterListUseCase
import com.aqube.ram.presentation.fakes.FakePresentationData
import com.aqube.ram.presentation.utils.PresentationBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterListViewModelTest : PresentationBaseTest() {

    @Mock
    lateinit var charactersUseCase: GetCharacterListUseCase

    @Mock
    lateinit var bookmarkUseCase: GetBookmarkCharacterListUseCase

    @Mock
    private lateinit var observer: Observer<CharacterUIModel>

    private lateinit var sut: CharacterListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = CharacterListViewModel(dispatcher, charactersUseCase, bookmarkUseCase)
        sut.getCharacters().observeForever(observer)
    }

    @Test
    fun `get characters should return character list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isBookmarked = false
            val characters = FakePresentationData.getCharacters(7)
            `when`(charactersUseCase(Unit)).thenReturn(flowOf(characters))

            // Act (When)
            sut.getCharacters(isBookmarked)

            // Assert (Then)
            verify(observer).onChanged(CharacterUIModel.Loading)
            verify(observer).onChanged(CharacterUIModel.Success(characters))
        }

    @Test
    fun `get characters should return empty character list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isBookmarked = false
            val characters = FakePresentationData.getCharacters(0)
            `when`(charactersUseCase(Unit)).thenReturn(flowOf(characters))

            // Act (When)
            sut.getCharacters(isBookmarked)

            // Assert (Then)
            verify(observer).onChanged(CharacterUIModel.Loading)
            verify(observer).onChanged(CharacterUIModel.Success(characters))
        }

    @Test
    fun `get characters should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isBookmarked = false
            val errorMessage = "Internal server error"
            whenever(charactersUseCase(Unit)) doAnswer { throw IOException(errorMessage) }

            // Act (When)
            sut.getCharacters(isBookmarked)

            // Assert (Then)
            verify(observer).onChanged(CharacterUIModel.Loading)
            verify(observer).onChanged(CharacterUIModel.Error(errorMessage))
        }

    @Test
    fun `get bookmark characters should return character list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isBookmarked = true
            val characters = FakePresentationData.getCharacters(3)
            `when`(bookmarkUseCase(Unit)).thenReturn(flowOf(characters))

            // Act (When)
            sut.getCharacters(isBookmarked)

            // Assert (Then)
            verify(observer).onChanged(CharacterUIModel.Loading)
            verify(observer).onChanged(CharacterUIModel.Success(characters))
        }

    @Test
    fun `get bookmark characters should return empty character list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isBookmarked = true
            val characters = FakePresentationData.getCharacters(0)
            `when`(bookmarkUseCase(Unit)).thenReturn(flowOf(characters))

            // Act (When)
            sut.getCharacters(isBookmarked)

            // Assert (Then)
            verify(observer).onChanged(CharacterUIModel.Loading)
            verify(observer).onChanged(CharacterUIModel.Success(characters))
        }

    @Test
    fun `get bookmark characters should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isBookmarked = true
            val errorMessage = "Internal server error"
            whenever(bookmarkUseCase(Unit)) doAnswer { throw IOException(errorMessage) }

            // Act (When)
            sut.getCharacters(isBookmarked)

            // Assert (Then)
            verify(observer).onChanged(CharacterUIModel.Loading)
            verify(observer).onChanged(CharacterUIModel.Error(errorMessage))
        }

    @After
    fun tearDown() {
        sut.onCleared()
    }
}
