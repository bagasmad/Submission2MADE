package com.example.submission3jetpack.ui.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData
import com.example.submission3jetpack.data.source.MovieTvShowRepository


class DetailViewModel(private val movieTvShowRepository: MovieTvShowRepository) : ViewModel() {
    private lateinit var dataTv: TvData
    private lateinit var dataMovie: MoviesData

    val favoriteCheck = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun getTvData(): TvData {
        return dataTv
    }

    fun setTvData(data: TvData) {
        dataTv = data
    }

    fun getMovieData(): MoviesData {
        return dataMovie
    }

    fun setMovieData(data: MoviesData) {
        dataMovie = data
    }

    fun updateFavoriteMovie(moviesData: MoviesData, state: Boolean)
    {
        movieTvShowRepository.updateFavoriteMovie(moviesData, state)
        favoriteCheck.postValue(state)
    }


    fun updateFavoriteTvShow(tvData: TvData, state: Boolean)
    {
        movieTvShowRepository.updateFavoriteTvShow(tvData, state)
        favoriteCheck.postValue(state)
    }

}