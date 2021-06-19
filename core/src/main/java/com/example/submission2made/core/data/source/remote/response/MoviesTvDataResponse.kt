package com.example.submission2made.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesTvDataResponse(

    @SerializedName("id")
    val id: Int,

    @field: SerializedName("original_name", alternate = ["original_title"])
    val original_title: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("vote_average")
    val vote_average: Double,

    @SerializedName("vote_count")
    val vote_count: Int,

    @SerializedName("original_language")
    val original_language: String,

    @SerializedName("popularity")
    val popularity: Double,

    var favorite: Boolean = false,) : Parcelable

