package com.example.movieviewerr.repository

import com.example.movieviewerr.model.api.AuthInterceptor
import com.example.movieviewerr.model.api.MoviesService
import com.example.movieviewerr.utils.Constants
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val moviesService: MoviesService,
    private val authInterceptor: AuthInterceptor
) {

    fun requestMovies() = moviesService.getMovies(Constants.API_KEY)

}