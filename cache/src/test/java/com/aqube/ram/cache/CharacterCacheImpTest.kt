package com.aqube.ram.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aqube.ram.cache.mapper.CharacterCacheMapper
import com.aqube.ram.cache.utils.CacheBaseTest
import com.aqube.ram.cache.utils.CachePreferencesHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharacterCacheImpTest : CacheBaseTest() {

    @Mock
    lateinit var characterCacheMapper: CharacterCacheMapper

    @Mock
    lateinit var preferencesHelper: CachePreferencesHelper

    lateinit var sut: CharacterCacheImp

    @Before
    override fun setup() {
        super.setup()
        sut = CharacterCacheImp(charaterDao, characterCacheMapper, preferencesHelper)
    }


}