package com.example.submission2made.core.data.source

import com.example.submission2made.core.data.source.local.LocalDataSource
import com.example.submission2made.core.data.source.remote.RemoteDataSource
import com.example.submission2made.core.data.source.remote.network.ApiResponse
import com.example.submission2made.core.data.source.remote.response.MoviesTvDataResponse
import com.example.submission2made.core.domain.model.Movie
import com.example.submission2made.core.domain.model.Tv
import com.example.submission2made.core.domain.repository.IRepository
import com.example.submission2made.core.utils.AppExecutors
import com.example.submission2made.core.utils.DataMapper
import com.example.submission2made.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieTvShowRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IRepository {

    override fun getMovies(): Flow<Resource<List<Movie>>> {
        return object :
            NetworkBoundResource<List<Movie>, List<MoviesTvDataResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesTvDataResponse>>> =
                remoteDataSource.getMovies()

            override suspend fun saveCallResult(data: List<MoviesTvDataResponse>) {
                val moviesList = DataMapper.mapResponsesToMoviesEntity(data)
                localDataSource.insertMovies(moviesList)
            }
        }.asFlow()
    }


    override fun getTvShows(): Flow<Resource<List<Tv>>> {
        return object :
            NetworkBoundResource<List<Tv>, List<MoviesTvDataResponse>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Tv>> =
                localDataSource.getTvShows().map { DataMapper.mapTvEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Tv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesTvDataResponse>>> =
                remoteDataSource.getTvShow()

            override suspend fun saveCallResult(data: List<MoviesTvDataResponse>) {
                val tvList = DataMapper.mapResponsesToTvEntity(data)
                localDataSource.insertTvShows(tvList)
            }
        }.asFlow()
    }

    override fun updateFavoriteTvShow(tv: Tv, state: Boolean) {
        val tvEntity = DataMapper.mapDomainToTvEntity(tv)
        appExecutors.diskIO().execute { localDataSource.updateFavoriteTvShow(tvEntity, state) }
    }

    override fun updateFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToMovieEntity(movie)
        appExecutors.diskIO().execute { localDataSource.updateFavoriteMovie(movieEntity, state) }
    }


    override fun getFavoriteMovies(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovies().map { DataMapper.mapMovieEntitiesToDomain(it) }

    override fun getFavoriteTvShows(): Flow<List<Tv>> =
        localDataSource.getFavoriteTvShows().map { DataMapper.mapTvEntitiesToDomain(it) }


}