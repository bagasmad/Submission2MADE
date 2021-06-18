package com.example.submission2made.core.utils

import com.example.submission2made.core.data.source.local.entity.MovieEntity
import com.example.submission2made.core.data.source.local.entity.TvEntity
import com.example.submission2made.core.data.source.remote.response.MoviesTvDataResponse
import com.example.submission2made.core.domain.model.Movie
import com.example.submission2made.core.domain.model.Tv

object DataMapper {
    fun mapResponsesToMoviesEntity(input: List<MoviesTvDataResponse>): List<MovieEntity> {
        val moviesList = arrayListOf<MovieEntity>()
        input.map {
            val movies = MovieEntity(
                id = it.id,
                original_title = it.original_title,
                poster_path = it.poster_path,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                original_language = it.original_language,
                popularity = it.popularity,
                favorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapResponsesToTvEntity(input: List<MoviesTvDataResponse>): List<TvEntity> {
        val tvList = arrayListOf<TvEntity>()
        input.map {
            val tvShow = TvEntity(
                id = it.id,
                original_title = it.original_title,
                poster_path = it.poster_path,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                original_language = it.original_language,
                popularity = it.popularity,
                favorite = false
            )
            tvList.add(tvShow)
        }
        return tvList
    }

    fun mapDomainToMovieEntity(input: Movie) = MovieEntity(
        id = input.id,
        original_title = input.original_title,
        poster_path = input.poster_path,
        overview = input.overview,
        vote_average = input.vote_average,
        vote_count = input.vote_count,
        original_language = input.original_language,
        popularity = input.popularity,
        favorite = input.favorite
    )


    fun mapDomainToTvEntity(input: Tv) = TvEntity(
        id = input.id,
        original_title = input.original_title,
        poster_path = input.poster_path,
        overview = input.overview,
        vote_average = input.vote_average,
        vote_count = input.vote_count,
        original_language = input.original_language,
        popularity = input.popularity,
        favorite = input.favorite
    )

    fun mapTvEntitiesToDomain(input: List<TvEntity>): List<Tv> {
        return input.map {
            Tv(
                id = it.id,
                original_title = it.original_title,
                poster_path = it.poster_path,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                original_language = it.original_language,
                popularity = it.popularity,
                favorite = it.favorite
            )
        }

    }


    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> {
        return input.map {
            Movie(
                id = it.id,
                original_title = it.original_title,
                poster_path = it.poster_path,
                overview = it.overview,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
                original_language = it.original_language,
                popularity = it.popularity,
                favorite = it.favorite
            )
        }

    }


}


