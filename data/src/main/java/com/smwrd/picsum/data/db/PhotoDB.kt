package com.smwrd.picsum.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.smwrd.picsum.data.db.PhotoDao
import com.smwrd.picsum.data.model.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1, exportSchema = false)
abstract class PhotoDB : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}