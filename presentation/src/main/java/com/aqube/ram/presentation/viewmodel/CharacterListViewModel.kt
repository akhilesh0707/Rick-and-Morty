package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.aqube.ram.domain.interactor.GetCharacterListUseCase
import com.aqube.ram.presentation.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect

class CharacterListViewModel @ViewModelInject constructor(
    private var getCharacterListUseCase: GetCharacterListUseCase
) : BaseViewModel() {

    private var getCharactersJob: Job? = null
    //
    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        /*_favoriteViewState.value =
            _favoriteViewState.value?.copy(isLoading = false, error = Error(message))*/
    }


    init {
        Log.d("ViewModel", "Viewmodel called")
        getCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }

    fun getCharacters() {
        getCharactersJob = launchCoroutine {
            loadFavorites()

        }
    }

    private suspend fun loadFavorites() {

        getCharacterListUseCase(Unit).collect {
            Log.d("", it.toString())
        }
    }

}