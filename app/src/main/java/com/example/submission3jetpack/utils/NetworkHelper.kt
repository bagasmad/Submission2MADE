package com.example.submission3jetpack.utils

import com.example.submission3jetpack.retrofit.RetrofitInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {
    fun getService(): RetrofitInterface {
        val httpClient = OkHttpClient.Builder()
        val baseUrl = "https://api.themoviedb.org/3/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(httpClient.build())
            .build()
        return retrofit.create(RetrofitInterface::class.java)
    }
}