package com.example.submission2made.core.retrofit

import com.example.submission2made.core.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface {
    @GET("tv/on_the_air?")

    suspend fun getTvShows(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
    ): ResponseClass

    @GET("movie/now_playing?")
    suspend fun getMovies(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US",
    ): ResponseClass
}