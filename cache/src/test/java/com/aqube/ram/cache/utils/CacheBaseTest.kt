package com.aqube.ram.cache.utils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.aqube.ram.cache.dao.CharacterDao
import com.aqube.ram.cache.database.CharactersDatabase
import com.aqube.ram.data.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

@ExperimentalCoroutinesApi
abstract class CacheBaseTest {

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.dispatcher
    val exceptionHandler = TestCoroutineExceptionHandler()
    private lateinit var db: CharactersDatabase
    protected lateinit var charaterDao: CharacterDao

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, CharactersDatabase::class.java).build()
        charaterDao = db.cachedCharacterDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        dispatcher.runBlockingTest {
            db.clearAllTables()
        }
        db.close()
    }
}