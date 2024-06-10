package com.smwrd.picsum.di.hilt

import android.content.Context
import androidx.room.Room
import com.smwrd.picsum.data.api.PhotoService
import com.smwrd.picsum.data.db.PhotoDB
import com.smwrd.picsum.data.db.PhotoDao
import com.smwrd.picsum.data.repository.PhotoRepositoryImpl
import com.smwrd.picsum.data.repository.local.PhotoLocalDataSource
import com.smwrd.picsum.data.repository.local.PhotoLocalDataSourceImpl
import com.smwrd.picsum.data.repository.remote.PhotoRemoteDataSource
import com.smwrd.picsum.data.repository.remote.PhotoRemoteDataSourceImpl
import com.smwrd.picsum.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providePhotoDao(photoDatabase: PhotoDB): PhotoDao {
        return photoDatabase.photoDao()
    }

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context): PhotoDB {
        return Room.databaseBuilder(
            context,
            PhotoDB::class.java,
            "Photo.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiInterface: PhotoService): PhotoRemoteDataSource {
        return PhotoRemoteDataSourceImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(photoDao: PhotoDao): PhotoLocalDataSource {
        return PhotoLocalDataSourceImpl(photoDao)
    }

//    @Singleton
//    @Provides
//    fun provideKtorInterface(client: HttpClient): KtorInterface {
//        return KtorInterfaceImpl(client)
//    }

    @Singleton
    @Provides
    fun providePhotoRepository(
        photoRemoteDataSource: PhotoRemoteDataSource,
        photoLocalDataSource: PhotoLocalDataSource,
    ): PhotoRepository {
        return PhotoRepositoryImpl(photoRemoteDataSource, photoLocalDataSource)
    }
}