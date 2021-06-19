package com.example.submission2made.core.retrofit

import com.example.submission2made.core.data.source.remote.response.MoviesTvDataResponse
import com.google.gson.annotations.SerializedName

data class ResponseClass
    (
    @SerializedName("results")
    val results: List<MoviesTvDataResponse>
)