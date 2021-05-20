package com.example.submission3jetpack.utils

import com.example.submission3jetpack.retrofit.ResponseClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DataAPI {

    fun getAPIMovieData(callback: DataCallBack) {
        val service = NetworkHelper().getService()
        service.getMovies().enqueue(object : Callback<ResponseClass> {
            override fun onResponse(call: Call<ResponseClass>, response: Response<ResponseClass>) {
                response.body()?.let { callback.onAllDataReceived(it) }
            }

            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }

        })
    }

    fun getAPItvShowData(callback: DataCallBack) {

        val service = NetworkHelper().getService()
        service.getTvShows().enqueue(object : Callback<ResponseClass> {
            override fun onResponse(call: Call<ResponseClass>, response: Response<ResponseClass>) {
                response.body()?.let { callback.onAllDataReceived(it) }
            }

            override fun onFailure(call: Call<ResponseClass>, t: Throwable) {
                t.printStackTrace()
                EspressoIdlingResource.decrement()
            }

        })
    }

    interface DataCallBack {
        fun onAllDataReceived(responseClass: ResponseClass)
    }

}