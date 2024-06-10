package com.smwrd.picsum.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.smwrd.picsum.data.model.PhotoEntity

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo ORDER BY id ASC")
    fun getAll(): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<PhotoEntity>): List<Long>

    @Delete
    fun delete(photos: List<PhotoEntity>): Int
}