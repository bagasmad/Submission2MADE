package com.example.submission3jetpack.ui.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission3jetpack.data.room.FavoriteRepository
import com.example.submission3jetpack.data.room.FavoriteTvData

class FavoriteTvShowViewModel(application: Application) : ViewModel() {
    private val favoriteRepository = FavoriteRepository(application)

    fun getAllFavorite() = favoriteRepository.getAllFavoriteTvs()

    fun addFavorite(favoriteTvData: FavoriteTvData) = favoriteRepository.insertTv(favoriteTvData)

    fun deleteFavorite(title: String) = favoriteRepository.deleteTv(title)

    fun getFavorite(title: String) = favoriteRepository.getFavoriteTv(title)



}