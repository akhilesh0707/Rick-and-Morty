package com.aqube.ram.presentation.viewmodel

import androidx.lifecycle.Observer
import com.aqube.ram.domain.interactor.CharacterBookmarkUseCase
import com.aqube.ram.domain.interactor.CharacterUnBookmarkUseCase
import com.aqube.ram.domain.interactor.GetCharacterByIdUseCase
import com.aqube.ram.presentation.fakes.FakePresentationData
import com.aqube.ram.presentation.utils.PresentationBaseTest
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterDetailViewModelTest : PresentationBaseTest() {

    @Mock
    lateinit var charactersUseCase: GetCharacterByIdUseCase

    @Mock
    lateinit var bookmarkUseCase: CharacterBookmarkUseCase

    @Mock
    lateinit var unBookmarkUseCase: CharacterUnBookmarkUseCase

    @Mock
    private lateinit var observer: Observer<CharacterDetailUIModel>

    lateinit var sut: CharacterDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = CharacterDetailViewModel(
            dispatcher,
            charactersUseCase,
            bookmarkUseCase,
            unBookmarkUseCase
        )
        sut.getCharacter().observeForever(observer)
    }

    @Test
    fun `get character detail with character-id should return character complete detail from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            val character = FakePresentationData.getCharacters(1)[0]
            `when`(charactersUseCase(characterId)).thenReturn(flowOf(character))

            // Act (When)
            sut.getCharacterDetail(characterId)

            // Assert (Then)
            verify(observer).onChanged(CharacterDetailUIModel.Loading)
            verify(observer).onChanged(CharacterDetailUIModel.Success(character))
        }

}