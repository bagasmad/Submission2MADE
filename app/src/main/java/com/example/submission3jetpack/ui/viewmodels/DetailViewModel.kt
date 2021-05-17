package com.example.submission3jetpack.ui.viewmodels


import androidx.lifecycle.ViewModel
import com.example.submission3jetpack.data.MoviesTvData


class DetailViewModel : ViewModel() {
    private lateinit var dataTvMovie: MoviesTvData

    fun getData(): MoviesTvData {
        return dataTvMovie
    }

    fun setData(data: MoviesTvData) {
        dataTvMovie = data
    }
}