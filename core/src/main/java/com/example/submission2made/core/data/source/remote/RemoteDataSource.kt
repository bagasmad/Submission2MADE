package com.example.submission2made.core.data.source.remote

import android.util.Log
import com.example.submission2made.core.data.source.remote.network.ApiResponse
import com.example.submission2made.core.data.source.remote.response.MoviesTvDataResponse
import com.example.submission2made.core.retrofit.RetrofitInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(private val retrofitInterface: RetrofitInterface) {

    fun getMovies(): Flow<ApiResponse<List<MoviesTvDataResponse>>> {
        return flow {
            try {
                val response = retrofitInterface.getMovies()
                val resultsArray = response.results
                if (resultsArray.isNotEmpty()) {
                    emit(ApiResponse.Success(resultsArray))
                } else {
                    emit(ApiResponse.Empty("the response is empty"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error("the response is error"))
                Log.e("remote data source", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }

    fun getTvShow(): Flow<ApiResponse<List<MoviesTvDataResponse>>> {
        return flow {
            try {
                val response = retrofitInterface.getTvShows()
                val resultsArray = response.results
                if (resultsArray.isNotEmpty()) {
                    emit(ApiResponse.Success(resultsArray))
                } else {
                    emit(ApiResponse.Empty("the response is empty"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error("the response is error"))
            }
        }.flowOn(Dispatchers.IO)
    }
}