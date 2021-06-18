package com.example.submission2made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie
    (

    val id: Int,

    val original_title: String,

    val poster_path: String,

    val overview: String,

    val vote_average: Double,

    val vote_count: Int,

    val original_language: String,

    val popularity: Double,

    var favorite: Boolean = false,
) : Parcelable