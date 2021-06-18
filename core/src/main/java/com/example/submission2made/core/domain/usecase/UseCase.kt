package com.example.submission2made.core.domain.usecase

import com.example.submission2made.core.domain.model.Movie
import com.example.submission2made.core.domain.model.Tv
import com.example.submission2made.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface UseCase {

    fun getMovies(): Flow<Resource<List<Movie>>>

    fun getTvShows(): Flow<Resource<List<Tv>>>

    fun updateFavoriteTvShow(tv: Tv, state: Boolean)

    fun updateFavoriteMovie(movie: Movie, state: Boolean)

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getFavoriteTvShows(): Flow<List<Tv>>
}