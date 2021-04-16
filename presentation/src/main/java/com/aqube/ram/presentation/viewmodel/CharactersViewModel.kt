package com.aqube.ram.presentation.viewmodel

import android.util.Log
import com.aqube.ram.domain.interactor.GetCharacterBaseUseCase
import com.aqube.ram.presentation.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

internal class CharactersViewModel @Inject constructor(
    private val getCharacterBaseUseCase: GetCharacterBaseUseCase,
) : BaseViewModel() {

    // region Members

    private var getCharactersJob: Job? = null

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        /*_favoriteViewState.value =
            _favoriteViewState.value?.copy(isLoading = false, error = Error(message))*/
    }


    init {
        getCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }

    private fun getCharacters() {
        getCharactersJob = launchCoroutine {
            loadFavorites()
        }
    }

    private suspend fun loadFavorites() {
        getCharacterBaseUseCase(Unit).collect {
            Log.d("", it.toString())
        }
    }

}