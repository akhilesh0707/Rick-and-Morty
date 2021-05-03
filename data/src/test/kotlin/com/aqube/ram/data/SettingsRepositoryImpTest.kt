package com.aqube.ram.data

import com.aqube.ram.data.utils.DataBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SettingsRepositoryImpTest : DataBaseTest() {

    lateinit var sut: SettingsRepositoryImp

    @Before
    fun setUp() {
        val appVersion = "1.0"
        sut = SettingsRepositoryImp(appVersion)
    }


}