package com.example.submission3jetpack.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.submission3jetpack.data.source.MovieTvShowRepository


class MovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    val moviesList = movieTvShowRepository.getMovies()
}
