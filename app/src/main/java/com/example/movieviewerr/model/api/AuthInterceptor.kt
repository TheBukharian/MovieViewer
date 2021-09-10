package com.example.movieviewerr.model.api

import com.example.movieviewerr.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {

    @Volatile
    var host: String = Constants.DEFAULT_HOST

    @Volatile
    var scheme: String = Constants.DEFAULT_SCHEMA

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url().newBuilder()
            .scheme(scheme)
            .host(host)
            .build()

        val newRequest = request.newBuilder()


        return chain.proceed(
            newRequest
                .url(newUrl)
                .build()
        )


    }
}