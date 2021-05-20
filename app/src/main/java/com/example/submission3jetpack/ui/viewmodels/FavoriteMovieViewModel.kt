package com.example.submission3jetpack.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.source.MovieTvShowRepository

class FavoriteMovieViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    val favoriteMoviesList = movieTvShowRepository.getFavoriteMovies()
}