package com.aqube.ram.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.aqube.ram.domain.interactor.GetCharacterByIdUseCase
import com.aqube.ram.domain.interactor.GetCharacterListUseCase
import com.aqube.ram.presentation.utils.ExceptionHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class CharacterListViewModel @ViewModelInject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : BaseViewModel<CharacterState>() {

    private var state: CharacterState = CharacterState.Init
       private set(value) {
            field = value
            publishState(value)
        }

    override val stateObservable: MutableLiveData<CharacterState> by lazy {
        MutableLiveData<CharacterState>()
    }

    private var getCharactersJob: Job? = null

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        state = CharacterState.Error(message)
    }

    fun getCharacters() {
        state = CharacterState.Loading
        getCharactersJob = launchCoroutine {
            loadFavorites()
        }
    }

    private suspend fun loadFavorites() {
        getCharacterListUseCase(Unit).collect {
            state = CharacterState.Success(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }
}