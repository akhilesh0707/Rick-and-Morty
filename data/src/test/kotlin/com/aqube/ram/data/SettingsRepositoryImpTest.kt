package com.aqube.ram.data

import com.aqube.ram.data.utils.DataBaseTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
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

    @Test
    fun `get settings with night mode on should return success result settings list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isNightMode = true

            // Act (When)
            val settings = sut.getSettings(isNightMode).single()

            // Assert (Then)
            assertEquals(settings.size, 3)
            assertTrue(settings[0].selectedValue)
        }

    @Test
    fun `get settings with night mode off should return success result settings list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isNightMode = false

            // Act (When)
            val settings = sut.getSettings(isNightMode).single()

            // Assert (Then)
            assertEquals(settings.size, 3)
            assertFalse(settings[0].selectedValue)
        }
}
