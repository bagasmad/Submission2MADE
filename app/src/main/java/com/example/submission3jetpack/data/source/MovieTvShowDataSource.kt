package com.example.submission3jetpack.data.source

import androidx.lifecycle.LiveData
import com.example.submission3jetpack.data.MoviesTvData

interface MovieTvShowDataSource{
    fun getMovies(): LiveData<List<MoviesTvData>>
    fun getTvShows(): LiveData<List<MoviesTvData>>
}

