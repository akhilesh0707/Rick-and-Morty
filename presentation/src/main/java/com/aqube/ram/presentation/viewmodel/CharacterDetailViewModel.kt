package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aqube.ram.domain.interactor.CharacterBookmarkUseCase
import com.aqube.ram.domain.interactor.CharacterUnBookmarkUseCase
import com.aqube.ram.domain.interactor.GetCharacterByIdUseCase
import com.aqube.ram.domain.models.Character
import com.aqube.ram.presentation.utils.ExceptionHandler
import com.aqube.ram.presentation.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect

private const val TAG = "CharacterDetailVM"

class CharacterDetailViewModel @ViewModelInject constructor(
    private val characterByIdUseCase: GetCharacterByIdUseCase,
    private val bookmarkUserCase: CharacterBookmarkUseCase,
    private val unBookmarkUserCase: CharacterUnBookmarkUseCase
) : BaseViewModel() {

    private val _character = MutableLiveData<Resource<Character>>()
    val character: LiveData<Resource<Character>> = _character

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, exception.message ?: "Error ")
        val message = ExceptionHandler.parse(exception)
        _character.postValue(Resource.error(exception.message ?: "Error"))
    }

    fun getCharacterDetail(characterId: Long) {
        _character.postValue(Resource.loading(null))
        launchCoroutineIO {
            loadCharacter(characterId)
        }
    }

    private suspend fun loadCharacter(characterId: Long) {
        characterByIdUseCase(characterId).collect {
            Log.d(TAG, it.toString())
            _character.postValue(Resource.success(it))
        }
    }

    fun setBookmarkCharacter() {
        launchCoroutineIO {
            bookmarkUserCase(1).collect {
                Log.d(TAG, "Character bookmark status $it")
            }
        }
    }

    fun setUnBookmarkCharacter() {
        launchCoroutineIO {
            unBookmarkUserCase(1).collect {
                Log.d(TAG, "Character unBookmark status $it")
            }
        }
    }

}