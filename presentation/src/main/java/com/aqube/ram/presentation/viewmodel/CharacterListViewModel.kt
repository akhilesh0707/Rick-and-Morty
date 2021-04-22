package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aqube.ram.domain.interactor.GetCharacterListUseCase
import com.aqube.ram.domain.models.Character
import com.aqube.ram.presentation.utils.ExceptionHandler
import com.aqube.ram.presentation.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect

private const val TAG = "CharacterListViewModel"

class CharacterListViewModel @ViewModelInject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
) : BaseViewModel() {


    private val _characterList = MutableLiveData<Resource<List<Character>>>()
    val characterList: LiveData<Resource<List<Character>>> = _characterList

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, exception.message ?: "Error ")
        val message = ExceptionHandler.parse(exception)
        _characterList.postValue(Resource.error(exception.message ?: "Error"))
    }

    fun getCharacters() {
        _characterList.postValue(Resource.loading(null))
        launchCoroutineIO {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        getCharacterListUseCase(Unit).collect {
            Log.d(TAG, it.toString())
            _characterList.postValue(Resource.success(it))
        }
    }
}