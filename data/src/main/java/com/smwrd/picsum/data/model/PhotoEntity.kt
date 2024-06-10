package com.smwrd.picsum.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(

    @PrimaryKey(autoGenerate = false)
    val id:Int,

    @NonNull
    val author:String,

    val width:Int,

    val height:Int,

    @NonNull
    val url:String,

    @NonNull
    @ColumnInfo(name = "download_url")
    val downloadUrl:String
)
