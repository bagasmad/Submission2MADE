package com.example.submission3jetpack.di

import com.example.submission3jetpack.data.source.MovieTvShowRepository
import com.example.submission3jetpack.data.source.remote.RemoteDataSource
import com.example.submission3jetpack.utils.NetworkHelper

object Injection {
    fun provideRepository(): MovieTvShowRepository {

        val remoteDataSource = RemoteDataSource.getInstance(NetworkHelper())

        return MovieTvShowRepository.getInstance(remoteDataSource)
    }
    fun provideFavoriteRepository(): MovieTvShowRepository {

        val remoteDataSource = RemoteDataSource.getInstance(NetworkHelper())

        return MovieTvShowRepository.getInstance(remoteDataSource)
    }
}