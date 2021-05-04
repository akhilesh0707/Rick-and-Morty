package com.aqube.ram.data

import com.aqube.ram.data.fakes.FakeCharacters
import com.aqube.ram.data.mapper.CharacterMapper
import com.aqube.ram.data.repository.CharacterDataSource
import com.aqube.ram.data.source.CharacterDataSourceFactory
import com.aqube.ram.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryImpTest : DataBaseTest() {

    @Mock
    lateinit var dataSourceFactory: CharacterDataSourceFactory

    @Mock
    lateinit var characterMapper: CharacterMapper

    @Mock
    lateinit var dataSource: CharacterDataSource

    lateinit var sut: CharacterRepositoryImp

    @Before
    fun setUp() {
        sut = CharacterRepositoryImp(dataSourceFactory, characterMapper)
    }

    @Test
    fun `get characters with cached true should return cache character list`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isCached = true
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.isCached()) doReturn isCached
            `when`(dataSourceFactory.getDataStore(isCached)) doReturn dataSource
            `when`(dataSourceFactory.getDataStore(isCached).getCharacters()) doReturn FakeCharacters.getCharacters()

            // Act (When)
            val characters = sut.getCharacters().single()

            // Assert (Then)
            assertEquals(characters.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSource, times(1)).isCached()
            verify(dataSourceFactory, times(2)).getDataStore(isCached)
            verify(dataSourceFactory.getDataStore(isCached), times(1)).getCharacters()
            verify(characterMapper, times(2)).mapFromEntity(any())
        }

    @Test
    fun `get characters with cached true should return cache character list and saved the characters to local db`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            val isCached = true
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.isCached()) doReturn isCached
            `when`(dataSourceFactory.getDataStore(isCached)) doReturn dataSource
            `when`(dataSourceFactory.getDataStore(isCached).getCharacters()) doReturn FakeCharacters.getCharacters()

            // Act (When)
            val characters = sut.getCharacters().single()

            // Assert (Then)
            assertEquals(characters.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSource, times(1)).isCached()
            verify(dataSourceFactory, times(2)).getDataStore(isCached)
            verify(dataSourceFactory.getDataStore(isCached), times(1)).getCharacters()
            verify(characterMapper, times(2)).mapFromEntity(any())
            verify(characterMapper, times(2)).mapToEntity(anyOrNull())
            verify(dataSourceFactory.getCacheDataSource(), times(1)).saveCharacters(any())
        }
}