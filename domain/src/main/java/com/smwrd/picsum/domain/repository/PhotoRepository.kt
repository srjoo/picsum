package com.smwrd.picsum.domain.repository

import com.smwrd.picsum.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    fun getPhotos(): Flow<List<Photo>>
}