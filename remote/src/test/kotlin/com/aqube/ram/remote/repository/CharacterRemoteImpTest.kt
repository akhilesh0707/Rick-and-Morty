package com.aqube.ram.remote.repository

import com.aqube.ram.remote.api.CharacterService
import com.aqube.ram.remote.mappers.CharacterEntityMapper
import com.aqube.ram.remote.utils.RemoteBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterRemoteImpTest : RemoteBaseTest() {

    @Mock
    lateinit var characterService: CharacterService

    @Mock
    lateinit var mapper: CharacterEntityMapper
    lateinit var sut: CharacterRemoteImp

    @Before
    fun setUp() {
        sut = CharacterRemoteImp(characterService, mapper)
    }


}
