package com.example.submission3jetpack.data.source.remote

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
                instance ?: RemoteDataSource(networkHelper).apply { instance = this }
            }
    }

    fun getMovies(callback: LoadDataCallback) {
        EspressoIdlingResource.increment()
        networkHelper.getService().getMovies().enqueue(object : Callback<ResponseClass> {
            override fun onResponse(call: Call<ResponseClass>, response: Response<ResponseClass>) {
                response.body()?.let { callback.onAllDataReceived(it) }
            }

            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })

    }

    fun getTvShow(callback: LoadDataCallback) {
        EspressoIdlingResource.increment()
        networkHelper.getService().getTvShows().enqueue(object : Callback<ResponseClass> {
            override fun onResponse(call: Call<ResponseClass>, response: Response<ResponseClass>) {
                response.body()?.let { callback.onAllDataReceived(it) }
            }

            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
                EspressoIdlingResource.decrement()
            }
        })
    }

    interface LoadDataCallback {
        fun onAllDataReceived(responseClass: ResponseClass) {
        }
    }
}