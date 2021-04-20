package com.aqube.ram.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aqube.ram.cache.utils.CacheConstants

@Entity(tableName = CacheConstants.CHARACTER_LOCATION_TABLE_NAME)
data class CharacterLocationCacheEntity(
    @PrimaryKey
    val name: String,
    val url: String
)