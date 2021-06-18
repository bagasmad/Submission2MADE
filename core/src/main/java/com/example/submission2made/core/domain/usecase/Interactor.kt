package com.example.submission2made.core.domain.usecase

import com.example.submission2made.core.domain.model.Movie
import com.example.submission2made.core.domain.model.Tv
import com.example.submission2made.core.domain.repository.IRepository

class Interactor(private val repository: IRepository) : UseCase {
    override fun getMovies() = repository.getMovies()

    override fun getTvShows() = repository.getTvShows()

    override fun updateFavoriteTvShow(tv: Tv, state: Boolean) =
        repository.updateFavoriteTvShow(tv, state)

    override fun updateFavoriteMovie(movie: Movie, state: Boolean) =
        repository.updateFavoriteMovie(movie, state)

    override fun getFavoriteMovies() = repository.getFavoriteMovies()

    override fun getFavoriteTvShows() = repository.getFavoriteTvShows()


}