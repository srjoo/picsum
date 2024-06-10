package com.smwrd.picsum.domain.usecase

import com.smwrd.picsum.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) {
    /*
    operator fun invoke(
        query: String,
    ): Flowable<List<Movie>> = repository.getSearchMovies(query)

    fun getFlowData(
        query: String,
    ): Flow<List<Movie>> = repository.getSearchMoviesFlow(query)
     */
}