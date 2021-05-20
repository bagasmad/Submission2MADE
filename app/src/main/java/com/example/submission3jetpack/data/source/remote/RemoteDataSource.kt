package com.example.submission3jetpack.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission3jetpack.data.source.remote.response.MoviesTvDataResponse
import com.example.submission3jetpack.retrofit.ResponseClass
import com.example.submission3jetpack.utils.EspressoIdlingResource
import com.example.submission3jetpack.utils.NetworkHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemoteDataSource private constructor(private val networkHelper: NetworkHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(networkHelper: NetworkHelper): RemoteDataSource =
            instance ?: synchronized(this)
            {
                RemoteDataSource(networkHelper).apply { instance = this }
            }
    }

    fun getMovies(): LiveData<ApiResponse<List<MoviesTvDataResponse>>> {
        EspressoIdlingResource.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<MoviesTvDataResponse>>>()
        networkHelper.getService().getMovies().enqueue(object : Callback<ResponseClass> {
            override fun onResponse(call: Call<ResponseClass>, response: Response<ResponseClass>) {
                response.body()?.let {resultMovies.postValue(ApiResponse.success(it.results))}
            }

            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovies

    }

    fun getTvShow(): LiveData<ApiResponse<List<MoviesTvDataResponse>>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<MoviesTvDataResponse>>>()
        networkHelper.getService().getTvShows().enqueue(object : Callback<ResponseClass> {
            override fun onResponse(call: Call<ResponseClass>, response: Response<ResponseClass>) {
                response.body()?.let {resultTv.postValue(ApiResponse.success(it.results))}
            }

            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
        return resultTv
    }

    interface LoadDataCallback {
        fun onAllDataReceived(responseClass: ResponseClass) {
        }
    }
}