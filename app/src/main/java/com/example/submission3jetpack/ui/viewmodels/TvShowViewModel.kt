package com.example.submission3jetpack.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.submission3jetpack.data.source.MovieTvShowRepository

class TvShowViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    val tvShows = movieTvShowRepository.getTvShows()

}
