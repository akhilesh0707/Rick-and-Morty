package com.aqube.ram.presentation.viewmodel

import androidx.lifecycle.Observer
import com.aqube.ram.domain.interactor.GetSettingsUseCase
import com.aqube.ram.presentation.fakes.FakePresentationData
import com.aqube.ram.presentation.utils.PresentationBaseTest
import com.aqube.ram.presentation.utils.PresentationPreferencesHelper
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SettingsViewModelTest : PresentationBaseTest() {

    @Mock
    lateinit var settingsUseCase: GetSettingsUseCase

    @Mock
    lateinit var preferencesHelper: PresentationPreferencesHelper

    @Mock
    private lateinit var observer: Observer<SettingUIModel>

    lateinit var sut: SettingsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        sut = SettingsViewModel(dispatcher, settingsUseCase, preferencesHelper)
        sut.settings.observeForever(observer)
    }

    @Test
    fun `get settings with night mode on should return settings list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isNightMode = true
            `when`(preferencesHelper.isNightMode).thenReturn(isNightMode)
            val settings = FakePresentationData.getSettings(7)
            `when`(settingsUseCase(isNightMode)).thenReturn(flowOf(settings))

            // Act (When)
            sut.getSettings()

            // Assert (Then)
            verify(observer).onChanged(SettingUIModel.Loading)
            verify(observer).onChanged(SettingUIModel.Success(settings))
        }

    @Test
    fun `get settings with night mode off should return settings list from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isNightMode = false
            `when`(preferencesHelper.isNightMode).thenReturn(isNightMode)
            val settings = FakePresentationData.getSettings(7)
            `when`(settingsUseCase(isNightMode)).thenReturn(flowOf(settings))

            // Act (When)
            sut.getSettings()

            // Assert (Then)
            verify(observer).onChanged(SettingUIModel.Loading)
            verify(observer).onChanged(SettingUIModel.Success(settings))
        }

    @Test
    fun `get settings with night mode on should return settings list empty from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isNightMode = false
            `when`(preferencesHelper.isNightMode).thenReturn(isNightMode)
            val settings = FakePresentationData.getSettings(0)
            `when`(settingsUseCase(isNightMode)).thenReturn(flowOf(settings))

            // Act (When)
            sut.getSettings()

            // Assert (Then)
            verify(observer).onChanged(SettingUIModel.Loading)
            verify(observer).onChanged(SettingUIModel.Success(settings))
        }

    @Test
    fun `get settings with night mode on should return error from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val isNightMode = true
            `when`(preferencesHelper.isNightMode).thenReturn(isNightMode)
            val errorMessage = "Internal server error"
            whenever(settingsUseCase(isNightMode)) doAnswer { throw IOException(errorMessage) }

            // Act (When)
            sut.getSettings()

            // Assert (Then)
            verify(observer).onChanged(SettingUIModel.Loading)
            verify(observer).onChanged(SettingUIModel.Error(errorMessage))
        }

    @Test
    fun `set settings with night mode on should return night mode on from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val nightMode = true
            val setting = FakePresentationData.getSettings(1)[0]
            setting.selectedValue = nightMode

            // Act (When)
            sut.setSettings(setting)

            // Assert (Then)
            verify(preferencesHelper).isNightMode = nightMode
            verify(observer).onChanged(SettingUIModel.NightMode(nightMode))
        }

    @Test
    fun `set settings with night mode off should return night mode off from use-case`() =
        dispatcher.test.runBlockingTest {
            // Arrange (Given)
            val nightMode = false
            val setting = FakePresentationData.getSettings(1)[0]
            setting.selectedValue = nightMode

            // Act (When)
            sut.setSettings(setting)

            // Assert (Then)
            verify(preferencesHelper).isNightMode = nightMode
            verify(observer).onChanged(SettingUIModel.NightMode(nightMode))
        }

    @After
    fun tearDown() {
        sut.onCleared()
    }
}
