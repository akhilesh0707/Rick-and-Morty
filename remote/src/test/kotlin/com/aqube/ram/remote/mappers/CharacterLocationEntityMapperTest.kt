package com.aqube.ram.remote.mappers

import com.aqube.ram.data.models.CharacterLocationEntity
import com.aqube.ram.remote.fakes.FakeRemoteData
import com.aqube.ram.remote.utils.RemoteBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterLocationEntityMapperTest : RemoteBaseTest() {

    lateinit var sut: CharacterLocationEntityMapper

    @Before
    fun setUp() {
        sut = CharacterLocationEntityMapper()
    }

    @Test
    fun `map model to entity should return converted object`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val locationModel = FakeRemoteData.getCharacterModel(false).characterLocation

            // Act (When)
            val locationEntity = sut.mapFromModel(locationModel)

            // Assert (Then)
            assertThat(locationEntity, instanceOf(CharacterLocationEntity::class.java))
        }
}
