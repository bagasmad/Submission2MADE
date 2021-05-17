package com.example.submission3jetpack.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.submission3jetpack.data.room.FavoriteMovieData
import com.example.submission3jetpack.data.room.FavoriteRepository
import com.example.submission3jetpack.data.room.FavoriteTvData

class FavoriteMovieViewModel(application: Application) : ViewModel() {
    private val favoriteRepository = FavoriteRepository(application)

    fun getAllFavorite() = favoriteRepository.getAllFavoriteMovies()

    fun addFavorite(favoriteMovieData: FavoriteMovieData) = favoriteRepository.insertMovie(favoriteMovieData)

    fun deleteFavorite(title: String) = favoriteRepository.deleteMovie(title)

    fun getFavorite(title: String) = favoriteRepository.getFavoriteMovie(title)

}