package com.smwrd.picsum.data.api

import com.smwrd.picsum.data.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {
    @GET("v2/list")
    suspend fun getPhotoList (
        @Query("page") page:Int,
        @Query("limit") limit:Int
    ) : List<PhotoResponse>
}