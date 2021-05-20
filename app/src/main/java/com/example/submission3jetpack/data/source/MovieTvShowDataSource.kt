package com.example.submission3jetpack.data.source

import androidx.lifecycle.LiveData
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData
import com.example.submission3jetpack.vo.Resource

interface MovieTvShowDataSource{
    fun getMovies(): LiveData<Resource<List<MoviesData>>>

    fun getTvShows(): LiveData<Resource<List<TvData>>>

    fun updateFavoriteTvShow(tvData: TvData,state: Boolean)

    fun updateFavoriteMovie(moviesData: MoviesData,state: Boolean)

    fun getFavoriteMovies(): LiveData<List<MoviesData>>

    fun getFavoriteTvShows(): LiveData<List<TvData>>


}

