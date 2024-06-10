package com.smwrd.picsum.data.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    val id:Int,

    val author:String,

    val width:Int,

    val height:Int,

    val url:String,

    @SerializedName("download_url")
    val downloadUrl:String
)
