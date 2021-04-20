package com.aqube.ram.cache.utils

import androidx.room.TypeConverter
import com.aqube.ram.cache.models.CharacterLocationCacheEntity

class Converters {
    companion object {


        @TypeConverter
        @JvmStatic
        fun fromLocation(value: CharacterLocationCacheEntity): String {
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun toToLocation(value: String): CharacterLocationCacheEntity {
            return CharacterLocationCacheEntity("","")
        }
    }
}