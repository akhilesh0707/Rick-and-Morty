package com.aqube.ram.cache.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.aqube.ram.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.CHARACTER_TABLE_NAME)
data class CharacterCache(
    val created: String,
    val gender: String,
    val id: Int,
    val image: String,
    @ColumnInfo(name = "character_location")
    val characterLocation: CharacterLocationCache,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    @ColumnInfo(name = "is_bookmarked")
    var isBookMarked: Boolean
)