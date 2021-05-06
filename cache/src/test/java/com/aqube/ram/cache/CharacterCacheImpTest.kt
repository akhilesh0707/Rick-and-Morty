package com.aqube.ram.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aqube.ram.cache.fakes.FakeCacheData
import com.aqube.ram.cache.mapper.CharacterCacheMapper
import com.aqube.ram.cache.mapper.CharacterLocationCacheMapper
import com.aqube.ram.cache.utils.CacheBaseTest
import com.aqube.ram.cache.utils.CachePreferencesHelper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class CharacterCacheImpTest : CacheBaseTest() {

    private val locationMapper = CharacterLocationCacheMapper()
    private val characterCacheMapper = CharacterCacheMapper(locationMapper)
    private lateinit var preferencesHelper: CachePreferencesHelper
    lateinit var sut: CharacterCacheImp

    @Before
    override fun setup() {
        super.setup()
        preferencesHelper = CachePreferencesHelper(context)
        sut = CharacterCacheImp(charaterDao, characterCacheMapper, preferencesHelper)
    }

    @Test
    fun `get characters should return success characters from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterEntity = FakeCacheData.getFakeCharacterEntity(7)
            sut.saveCharacters(characterEntity)
            // Act (When)
            val characters = sut.getCharacters()

            // Assert (Then)
            assertEquals(characters.size, 7)
        }

}