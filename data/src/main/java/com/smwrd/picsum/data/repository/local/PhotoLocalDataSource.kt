package com.smwrd.picsum.data.repository.local

import com.smwrd.picsum.data.model.PhotoEntity

interface PhotoLocalDataSource {
    fun insertPhoto(photo: List<PhotoEntity>):List<Long>
    fun getAllPhotos(): List<PhotoEntity>
    fun deletePhotos(photo: List<PhotoEntity>): Int
}