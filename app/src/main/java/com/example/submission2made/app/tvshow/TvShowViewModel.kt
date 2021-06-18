package com.example.submission2made.app.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.submission2made.core.domain.usecase.UseCase

class TvShowViewModel(private val useCase: UseCase) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()

    fun getTvShows() = useCase.getTvShows().asLiveData()
}
