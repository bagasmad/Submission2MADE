package com.example.submission2made.favorite.favoritetvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submission2made.core.domain.usecase.UseCase

class FavoriteTvShowViewModel(private val useCase: UseCase) :
    ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    fun getFavoriteTvShows() = useCase.getFavoriteTvShows().asLiveData()

}