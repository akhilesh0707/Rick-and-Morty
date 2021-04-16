package com.aqube.ram.remote.models

import com.squareup.moshi.Json

data class CharacterListModel(
    val info: InfoModel,
    @field:Json(name = "results")
    val characters: List<CharacterModel>
)