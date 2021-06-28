package com.aqube.ram.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aqube.ram.cache.models.CharacterCacheEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): List<CharacterCacheEntity>

    @Query("SELECT * FROM characters WHERE  id = :id")
    fun getCharacter(id: Long): CharacterCacheEntity

    @Query("SELECT * FROM characters WHERE is_bookmarked = 1")
    fun getBookMarkedCharacters(): List<CharacterCacheEntity>

    @Query("DELETE FROM characters")
    fun clearCharacters(): Int

    @Query("UPDATE characters SET is_bookmarked = 1 WHERE id = :id")
    fun bookmarkCharacter(id: Long): Int

    @Query("UPDATE characters SET is_bookmarked = 0 WHERE id = :id")
    fun unBookmarkCharacter(id: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacter(vararg character: CharacterCacheEntity)
}
