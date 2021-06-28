package com.aqube.ram.cache

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aqube.ram.cache.fakes.FakeCacheData
import com.aqube.ram.cache.mapper.CharacterCacheMapper
import com.aqube.ram.cache.mapper.CharacterLocationCacheMapper
import com.aqube.ram.cache.utils.CacheBaseTest
import com.aqube.ram.cache.utils.CachePreferencesHelper
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
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
            // Saving characters to database so we can get it when select query executes
            sut.saveCharacters(characterEntity)

            // Act (When)
            val characters = sut.getCharacters()

            // Assert (Then)
            assertEquals(characters.size, 7)
        }

    @Test
    fun `get characters should return success characters from local room cache with empty list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) no arrange

            // Act (When)
            val characters = sut.getCharacters()

            // Assert (Then)
            assertTrue(characters.isEmpty())
        }

    @Test
    fun `get character with specific id should return success character from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            val characterEntity = FakeCacheData.getFakeCharacterEntity(1, false)
            // Saving characters to database so we can get it when select query executes
            sut.saveCharacters(characterEntity)

            // Act (When)
            val character = sut.getCharacter(characterId)

            // Assert (Then)
            assertNotNull(character)
            assertEquals(character.id, 1)
        }

    @Test
    fun `save character should return saved characters from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterEntity = FakeCacheData.getFakeCharacterEntity(7)
            // Act (When)
            sut.saveCharacters(characterEntity)
            val characters = sut.getCharacters()
            // Assert (Then)
            assertEquals(characters.size, 7)
        }

    @Test
    fun `get bookmark characters should return success bookmarked characters from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            // Saving characters to database so we can get it when select query executes
            val characterEntity =
                FakeCacheData.getFakeCharacterEntity(size = 7, isBookmarked = true)
            sut.saveCharacters(characterEntity)

            // Act (When)
            val bookmarkCharacters = sut.getBookMarkedCharacters()

            // Assert (Then)
            assertEquals(bookmarkCharacters.size, 7)
        }

    @Test
    fun `get bookmark characters should return success bookmarked characters from local room cache with empty list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given) no arrange

            // Act (When)
            val characters = sut.getBookMarkedCharacters()

            // Assert (Then)
            assertTrue(characters.isEmpty())
        }

    @Test
    fun `set bookmark with specific id should return success status from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            // Saving characters to database so we can get it when select query executes
            val characterEntity = FakeCacheData.getFakeCharacterEntity(1, false)
            sut.saveCharacters(characterEntity)

            // Act (When)
            val bookmarkStatus = sut.setCharacterBookmarked(characterId)

            // Assert (Then)
            assertEquals(bookmarkStatus, 1)
        }

    @Test
    fun `set bookmark with specific id should return fail status from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L

            // Act (When)
            val bookmarkStatus = sut.setCharacterBookmarked(characterId)

            // Assert (Then)
            assertEquals(bookmarkStatus, 0)
        }

    @Test
    fun `set un-bookmark with specific id should return success status from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L
            // Saving characters to database so we can get it when select query executes
            val characterEntity = FakeCacheData.getFakeCharacterEntity(1, false)
            sut.saveCharacters(characterEntity)

            // Act (When)
            val bookmarkStatus = sut.setCharacterUnBookMarked(characterId)

            // Assert (Then)
            assertEquals(bookmarkStatus, 1)
        }

    @Test
    fun `set un-bookmark with specific id should return fail status from local room cache`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val characterId = 1L

            // Act (When)
            val bookmarkStatus = sut.setCharacterUnBookMarked(characterId)

            // Assert (Then)
            assertEquals(bookmarkStatus, 0)
        }

    @Test
    fun `is cached should return success true`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            // Saving characters to database so we can get it when select query executes
            val characterEntity = FakeCacheData.getFakeCharacterEntity(1, false)
            sut.saveCharacters(characterEntity)

            // Act (When)
            val isCached = sut.isCached()

            // Assert (Then)
            assertTrue(isCached)
        }

    @Test
    fun `is cached should return success false`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            // Act (When)
            val isCached = sut.isCached()

            // Assert (Then)
            assertFalse(isCached)
        }

    @Test
    fun `set last cached time should return saved time`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val time = 1000L
            // Act (When)
            sut.setLastCacheTime(time)
            val lastCacheTime = preferencesHelper.lastCacheTime

            // Assert (Then)
            assertEquals(lastCacheTime, lastCacheTime)
        }

    @Test
    fun `is expired cache time cached true`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            // Act (When)
            val isExpired = sut.isExpired()

            // Assert (Then)
            assertTrue(isExpired)
        }

    @Test
    fun `is expired cache time cached false`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val time = System.currentTimeMillis()
            sut.setLastCacheTime(time)
            // Act (When)

            val isExpired = sut.isExpired()

            // Assert (Then)
            assertFalse(isExpired)
        }
}
