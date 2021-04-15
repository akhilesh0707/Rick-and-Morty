package com.aqube.ram.remote.models

import com.squareup.moshi.Json

data class CharacterModel(
    val created: String,
    val episode: List<Any>,
    val gender: String,
    val id: Int,
    val image: String,
    @Json(name = "location")
    val characterLocation: CharacterLocationModel,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)