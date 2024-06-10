package com.smwrd.picsum.data.mapper

import com.smwrd.picsum.data.model.PhotoEntity
import com.smwrd.picsum.data.model.PhotoResponse
import com.smwrd.picsum.domain.model.Photo

fun mapperToPhotos(photos: List<PhotoEntity>): List<Photo> {
    return photos.toList().map {
        Photo(
            it.id,
            it.author,
            it.width,
            it.height,
            it.downloadUrl
        )
    }
}

fun mapperResponseToPhotos(photos: List<PhotoResponse>): List<Photo> {
    return photos.toList().map {
        Photo (
            it.id,
            it.author,
            it.width,
            it.height,
            it.downloadUrl
        )
    }
}