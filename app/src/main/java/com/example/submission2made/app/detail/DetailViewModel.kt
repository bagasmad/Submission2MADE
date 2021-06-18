package com.example.submission2made.app.detail


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2made.core.domain.model.Movie
import com.example.submission2made.core.domain.model.Tv
import com.example.submission2made.core.domain.usecase.UseCase


class DetailViewModel(private val useCase: UseCase) : ViewModel() {
    private lateinit var dataTv: Tv
    private lateinit var dataMovie: Movie

    val favoriteCheck = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun getTvData(): Tv {
        return dataTv
    }

    fun setTvData(tvEntity: Tv) {
        dataTv = tvEntity
    }

    fun getMovieData(): Movie {
        return dataMovie
    }

    fun setMovieData(movie: Movie) {
        dataMovie = movie
    }

    fun updateFavoriteMovie(movie: Movie, state: Boolean) {
        useCase.updateFavoriteMovie(movie, state)
        favoriteCheck.postValue(state)
    }


    fun updateFavoriteTvShow(tv: Tv, state: Boolean) {
        useCase.updateFavoriteTvShow(tv, state)
        favoriteCheck.postValue(state)
    }

}