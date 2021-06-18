package com.example.submission2made.core.data.source.local

import com.example.submission2made.core.data.source.local.entity.MovieEntity
import com.example.submission2made.core.data.source.local.entity.TvEntity
import com.example.submission2made.core.data.source.local.room.MovieTvDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: MovieTvDao) {

    suspend fun insertMovies(movies: List<MovieEntity>) = dao.insertMovies(movies)

    fun getMovies(): Flow<List<MovieEntity>> = dao.getMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = dao.getFavoriteMovies()

    fun updateFavoriteMovie(movie: MovieEntity, state: Boolean) {
        movie.favorite = state
        dao.updateMovie(movie)
    }

    suspend fun insertTvShows(tvShows: List<TvEntity>) = dao.insertTvShows(tvShows)

    fun getTvShows(): Flow<List<TvEntity>> = dao.getTvShows()

    fun getFavoriteTvShows(): Flow<List<TvEntity>> = dao.getFavoriteTvShows()

    fun updateFavoriteTvShow(tvShow: TvEntity, state: Boolean) {
        tvShow.favorite = state
        dao.updateTvShow(tvShow)
    }

}