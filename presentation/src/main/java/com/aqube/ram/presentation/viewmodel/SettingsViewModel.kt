package com.aqube.ram.presentation.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aqube.ram.domain.interactor.GetSettingsUseCase
import com.aqube.ram.domain.models.Settings
import com.aqube.ram.presentation.utils.ExceptionHandler
import com.aqube.ram.presentation.utils.PresentationPreferencesHelper
import com.aqube.ram.presentation.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect

class SettingsViewModel @ViewModelInject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val preferencesHelper: PresentationPreferencesHelper
) : BaseViewModel() {

    private val _settings = MutableLiveData<Resource<List<Settings>>>()
    val settings: LiveData<Resource<List<Settings>>> = _settings

    private val _nightMode = MutableLiveData<Resource<Boolean>>()
    val nightMode: LiveData<Resource<Boolean>> = _nightMode

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _settings.postValue(Resource.error(exception.message ?: "Error"))
    }

    fun getSettings() {
        _settings.postValue(Resource.loading(null))
        launchCoroutineIO {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        getSettingsUseCase(Unit).collect {
            Log.d("Updated list", it.toString())
            _settings.postValue(Resource.success(it))
        }
    }

    fun setSettings(selectedSetting: Settings) {
        selectedSetting.run {
            preferencesHelper.isNightMode = selectedValue
            _nightMode.postValue(Resource.success(selectedValue))
        }
        Log.d("SettingsViewModel", selectedSetting.toString())
        Log.d("Is night mode", preferencesHelper.isNightMode.toString())
    }
}