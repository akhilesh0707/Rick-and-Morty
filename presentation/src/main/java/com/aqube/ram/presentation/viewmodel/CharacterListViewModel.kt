package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import com.aqube.ram.domain.interactor.GetCharacterListUseCase
import com.aqube.ram.domain.interactor.GetFavoriteCharacterListUseCase
import com.aqube.ram.domain.models.Character
import com.aqube.ram.presentation.utils.ExceptionHandler
import com.aqube.ram.presentation.utils.UiAwareLiveData
import com.aqube.ram.presentation.utils.UiAwareModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect

private const val TAG = "CharacterListViewModel"

sealed class CharacterUIModel : UiAwareModel() {
    object Loading : CharacterUIModel()
    data class Error(var error: String = "") : CharacterUIModel()
    data class Success(val data: List<Character>) : CharacterUIModel()
}

class CharacterListViewModel @ViewModelInject constructor(
    private val characterListUseCase: GetCharacterListUseCase,
    private val favoriteCharacterListUseCase: GetFavoriteCharacterListUseCase
) : BaseViewModel() {

    private val _characterList = UiAwareLiveData<CharacterUIModel>()
    private var characterList: LiveData<CharacterUIModel> = _characterList

    fun getCharacters(): LiveData<CharacterUIModel> {
        return characterList
    }

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, exception.message ?: "Error ")
        val message = ExceptionHandler.parse(exception)
        _characterList.postValue(CharacterUIModel.Error(exception.message ?: "Error"))
    }

    fun getCharacters(isFavorite: Boolean) {
        _characterList.postValue(CharacterUIModel.Loading)
        launchCoroutineIO {
            if (isFavorite)
                loadFavoriteCharacters()
            else
                loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        characterListUseCase(Unit).collect {
            Log.d(TAG, "called again: $it")
            _characterList.postValue(CharacterUIModel.Success(it))
        }
    }

    private suspend fun loadFavoriteCharacters() {
        favoriteCharacterListUseCase(Unit).collect {
            Log.d(TAG, "called again: $it")
            _characterList.postValue(CharacterUIModel.Success(it))
        }
    }
}