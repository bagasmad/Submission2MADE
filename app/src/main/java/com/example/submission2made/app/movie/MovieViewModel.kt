package com.example.submission2made.app.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submission2made.core.domain.usecase.UseCase


class MovieViewModel(private val useCase: UseCase) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    fun getMovies() = useCase.getMovies().asLiveData()
}
