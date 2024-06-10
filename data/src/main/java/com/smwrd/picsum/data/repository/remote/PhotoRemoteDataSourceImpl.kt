package com.smwrd.picsum.data.repository.remote

import com.smwrd.picsum.data.api.PhotoService
import com.smwrd.picsum.data.model.PhotoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(
    private val photoService: PhotoService
) : PhotoRemoteDataSource {
    override fun getPhotosFlow(page: Int, limit: Int): Flow<List<PhotoResponse>> {
        return flow {
            emit(photoService.getPhotoList(
                page = page,
                limit = limit))
        }
    }
}