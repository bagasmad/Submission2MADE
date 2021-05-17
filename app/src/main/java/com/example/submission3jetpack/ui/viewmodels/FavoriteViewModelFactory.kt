package com.example.submission3jetpack.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission3jetpack.data.room.FavoriteRepository
import com.example.submission3jetpack.data.source.MovieTvShowRepository
import com.example.submission3jetpack.di.Injection

class FavoriteViewModelFactory private constructor(private val application: Application) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: FavoriteViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): FavoriteViewModelFactory
        {
            if(instance==null)
            {
                synchronized(FavoriteViewModelFactory::class.java)
                {
                    instance = FavoriteViewModelFactory(application)
                }
            }
        return instance as FavoriteViewModelFactory
        }

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                return FavoriteMovieViewModel(application) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                return FavoriteTvShowViewModel(application) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}