package com.example.submission3jetpack.data.source.local

import androidx.lifecycle.LiveData
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData
import com.example.submission3jetpack.data.source.local.room.MovieTvDao

class LocalDataSource private constructor(private val dao: MovieTvDao) {
    companion object
    {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: MovieTvDao) =
            INSTANCE?: LocalDataSource(dao)
    }

    fun insertMovies(movies: List<MoviesData>) = dao.insertMovies(movies)

    fun getMovies() : LiveData<List<MoviesData>> = dao.getMovies()

    fun getFavoriteMovies(): LiveData<List<MoviesData>> = dao.getFavoriteMovies()

    fun updateFavoriteMovie(movie: MoviesData, state: Boolean)
    {
        movie.favorite = state
        dao.updateMovie(movie)
    }

    fun getMovieByTitle(title: String): LiveData<MoviesData> = dao.getMovieByTitle(title)

    fun updateMovie(movie: MoviesData) = dao.updateMovie(movie)

    fun insertTvShows(tvShows: List<TvData>) = dao.insertTvShows(tvShows)

    fun getTvShows(): LiveData<List<TvData>> = dao.getTvShows()

    fun getFavoriteTvShows(): LiveData<List<TvData>> = dao.getFavoriteTvShows()

    fun getTvShowByTitle(title: String): LiveData<TvData> = dao.getTvByTitle(title)

    fun updateFavoriteTvShow(tvShow: TvData, state: Boolean)
    {
        tvShow.favorite = state
        dao.updateTvShow(tvShow)
    }

}