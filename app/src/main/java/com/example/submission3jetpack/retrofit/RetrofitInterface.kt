package com.example.submission3jetpack.retrofit

import com.example.submission3jetpack.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface {
    @GET("tv/on_the_air?")

    fun getTvShows(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<ResponseClass>

    @GET("movie/now_playing?")
    fun getMovies(
        @Query("api_key") api_key: String = BuildConfig.API_KEY,
        @Query("language") language: String = "en-US"
    ): Call<ResponseClass>
}