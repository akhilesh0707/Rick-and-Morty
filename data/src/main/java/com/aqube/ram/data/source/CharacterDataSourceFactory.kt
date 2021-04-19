package com.aqube.ram.data.source

import com.aqube.ram.data.repository.CharacterCache
import com.aqube.ram.data.repository.CharacterDataSource
import javax.inject.Inject

open class CharacterDataSourceFactory @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource
) {

    /*open fun getDataStore(isCached: Boolean): CharacterDataSource {
        return if (isCached && !characterCache.isExpired()) {
            return getCacheDataSource()
        } else {
            getRemoteDataSource()
        }
    }
*/
    fun getRemoteDataSource(): CharacterDataSource {
        return remoteDataSource
    }

    /*fun getCacheDataSource(): CharacterDataSource {
        return cacheDataSource
    }*/

}
