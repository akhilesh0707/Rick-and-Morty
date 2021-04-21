package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.aqube.ram.domain.interactor.GetCharacterByIdUseCase
import com.aqube.ram.domain.interactor.GetCharacterListUseCase
import com.aqube.ram.presentation.utils.ExceptionHandler
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

private const val TAG = "CharacterListViewModel"

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

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("TAG", exception?.message ?: "Error ")
        val message = ExceptionHandler.parse(exception)
        state = CharacterState.Error(message)
    }

    fun getCharacters() {
        state = CharacterState.Loading
        launchCoroutineIO {
            Log.d(TAG, "I'm working in thread ${Thread.currentThread().name}")
            loadFavorites()
        }
    }

    private suspend fun loadFavorites() {
        getCharacterListUseCase(Unit).collect {
            Log.d(TAG, it.toString())
            state = CharacterState.Success(it)
        }
    }


}