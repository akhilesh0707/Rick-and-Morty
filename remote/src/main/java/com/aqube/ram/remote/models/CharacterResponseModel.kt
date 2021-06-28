package com.aqube.ram.remote.models

import com.squareup.moshi.Json

data class CharacterResponseModel(
    @field:Json(name = "results")
    val characters: List<CharacterModel>
)
