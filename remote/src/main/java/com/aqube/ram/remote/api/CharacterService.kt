package com.aqube.ram.remote.api

import com.aqube.ram.remote.models.CharacterModel
import com.aqube.ram.remote.models.CharacterResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(): CharacterResponseModel

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Long): CharacterModel
}
