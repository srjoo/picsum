package com.smwrd.picsum.data.repository.local

import com.smwrd.picsum.data.db.PhotoDao
import com.smwrd.picsum.data.model.PhotoEntity
import javax.inject.Inject

class PhotoLocalDataSourceImpl @Inject constructor(
    private val photoDao: PhotoDao
) : PhotoLocalDataSource {
    override fun insertPhoto(photo: List<PhotoEntity>): List<Long> {
        return photoDao.insert(photo);
    }

    override fun getAllPhotos(): List<PhotoEntity> {
        return photoDao.getAll();
    }

    override fun deletePhotos(photo: List<PhotoEntity>): Int {
        return photoDao.delete(photo);
    }
}