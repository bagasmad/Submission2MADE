package com.example.submission3jetpack.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesTvDataResponse(
    @field: SerializedName("original_name", alternate = ["original_title"])
    val original_title: String,
    val poster_path: String,
    val overview: String,
    val vote_average: Double,
    val vote_count: Int,
    val original_language: String,
    val popularity: Double,
) : Parcelable

