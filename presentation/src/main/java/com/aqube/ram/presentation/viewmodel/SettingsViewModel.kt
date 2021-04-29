package com.aqube.ram.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aqube.ram.domain.interactor.GetSettingsUseCase
import com.aqube.ram.domain.models.Settings
import com.aqube.ram.presentation.utils.ExceptionHandler
import com.aqube.ram.presentation.utils.PresentationPreferencesHelper
import com.aqube.ram.presentation.utils.UiAwareModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect

sealed class SettingUIModel : UiAwareModel() {
    object Loading : SettingUIModel()
    data class Error(var error: String = "") : SettingUIModel()
    data class Success(val data: List<Settings>) : SettingUIModel()
    data class NightMode(val nightMode: Boolean) : SettingUIModel()
}

class SettingsViewModel @ViewModelInject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val preferencesHelper: PresentationPreferencesHelper
) : BaseViewModel() {

    private val _settings = MutableLiveData<SettingUIModel>()
    val settings: LiveData<SettingUIModel> = _settings

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _settings.postValue(SettingUIModel.Error(exception.message ?: "Error"))
    }

    fun getSettings() {
        _settings.postValue(SettingUIModel.Loading)
        launchCoroutineIO {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        getSettingsUseCase(preferencesHelper.isNightMode).collect {
            _settings.postValue(SettingUIModel.Success(it))
        }
    }

    fun setSettings(selectedSetting: Settings) {
        selectedSetting.run {
            preferencesHelper.isNightMode = selectedValue
            _settings.postValue(SettingUIModel.NightMode(selectedValue))
        }
    }
}