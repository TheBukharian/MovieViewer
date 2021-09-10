package com.example.movieviewerr.model.api

import com.example.movieviewerr.data.MoviesData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@JvmSuppressWildcards
interface MoviesService {

    @GET("3/movie/popular")
    fun getMovies(@Query("api_key") apiKey: String) : Single<MoviesData>


}