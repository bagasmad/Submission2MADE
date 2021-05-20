package com.example.submission3jetpack.utils

import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData

data class ParseJson
    (
    val movies: ArrayList<MoviesData>,
    val shows: ArrayList<TvData>,
)