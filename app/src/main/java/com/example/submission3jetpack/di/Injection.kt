package com.example.submission3jetpack.di

import android.content.Context
import com.example.submission3jetpack.data.source.MovieTvShowRepository
import com.example.submission3jetpack.data.source.local.LocalDataSource
import com.example.submission3jetpack.data.source.local.room.DatabaseMovieTv
import com.example.submission3jetpack.data.source.remote.RemoteDataSource
import com.example.submission3jetpack.utils.AppExecutors
import com.example.submission3jetpack.utils.NetworkHelper

object Injection {
    fun provideRepository(context: Context): MovieTvShowRepository {
        val databaseMovieTv = DatabaseMovieTv.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(NetworkHelper())
        val localDataSource = LocalDataSource.getInstance(databaseMovieTv.MovieTvDao())
        val appExecutors = AppExecutors()
        return MovieTvShowRepository.getInstance(remoteDataSource,localDataSource,appExecutors)

    }
}