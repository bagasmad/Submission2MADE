package com.example.submission2made.favorite.favoritemovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submission2made.core.domain.usecase.UseCase

class FavoriteMovieViewModel(private val useCase: UseCase) :
    ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    fun getFavoriteMovies() = useCase.getFavoriteMovies().asLiveData()

}