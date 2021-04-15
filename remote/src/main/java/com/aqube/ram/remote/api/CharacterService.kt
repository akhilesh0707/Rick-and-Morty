package com.aqube.ram.remote.api

import com.aqube.ram.remote.models.CharacterModel
import com.aqube.ram.remote.models.CharacterListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): CharacterListModel

    @GET("character/")
    suspend fun getCharacter(@Query("id") id: Int): CharacterModel
}
