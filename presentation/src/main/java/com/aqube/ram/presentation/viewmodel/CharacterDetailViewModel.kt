package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.aqube.ram.domain.interactor.GetCharacterByIdUseCase
import com.aqube.ram.presentation.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect

private const val TAG = "CharacterDetailVM"

class CharacterDetailViewModel @ViewModelInject constructor(
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
        Log.d(TAG, exception.message ?: "Error ")
        val message = ExceptionHandler.parse(exception)
        state = CharacterState.Error(message)
    }

    fun getCharacterDetail(characterId: Long) {
        state = CharacterState.Loading
        launchCoroutineIO {
            Log.d(TAG, "I'm working in thread ${Thread.currentThread().name}")
            loadCharacter(characterId)
        }
    }

    private suspend fun loadCharacter(characterId: Long) {
        getCharacterByIdUseCase(characterId).collect {
            Log.d(TAG, it.toString())
            //state = CharacterState.Success(it)
        }
    }


}