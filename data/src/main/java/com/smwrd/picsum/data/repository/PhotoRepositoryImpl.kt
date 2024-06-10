package com.smwrd.picsum.data.repository

import com.smwrd.picsum.data.mapper.mapperResponseToPhotos
import com.smwrd.picsum.data.repository.local.PhotoLocalDataSource
import com.smwrd.picsum.data.repository.remote.PhotoRemoteDataSource
import com.smwrd.picsum.domain.model.Photo
import com.smwrd.picsum.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val photoLocalDataSource: PhotoLocalDataSource,
) : PhotoRepository
{
    override fun getPhotos(): Flow<List<Photo>> {
        return flow {
            val photos = photoRemoteDataSource.getPhotosFlow(0, 200);
            photos.collect {
                emit(mapperResponseToPhotos(it))
            }
        }
    }
}