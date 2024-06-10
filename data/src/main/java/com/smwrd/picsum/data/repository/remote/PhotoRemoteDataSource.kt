package com.smwrd.picsum.data.repository.remote

import com.smwrd.picsum.data.model.PhotoResponse
import kotlinx.coroutines.flow.Flow

interface PhotoRemoteDataSource {
    fun getPhotosFlow(
        page: Int = 0,
        limit: Int = 20
    ): Flow<List<PhotoResponse>>
}