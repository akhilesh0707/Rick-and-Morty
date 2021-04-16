package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import com.aqube.ram.domain.interactor.GetCharacterByIdUseCase
import com.aqube.ram.domain.interactor.GetCharacterListUseCase
import com.aqube.ram.presentation.utils.ExceptionHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class CharacterListViewModel @ViewModelInject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BaseViewModel() {

    private var getCharactersJob: Job? = null

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
    }

    fun getCharacters() {
        getCharactersJob = launchCoroutine {
            loadFavorites()
        }
    }

    private suspend fun loadFavorites() {
        getCharacterListUseCase(Unit).collect {
            Log.d("List------------------------", it.toString())
        }

        getCharacterByIdUseCase(1).collect {
            Log.d("Character------------------------", it.toString())
        }
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }
}