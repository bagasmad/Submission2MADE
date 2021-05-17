package com.example.submission3jetpack.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.data.source.remote.RemoteDataSource
import com.example.submission3jetpack.retrofit.ResponseClass
import com.example.submission3jetpack.utils.EspressoIdlingResource

class MovieTvShowRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    MovieTvShowDataSource {
    companion object {
        @Volatile
        private var instance: MovieTvShowRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieTvShowRepository =
            instance ?: synchronized(this)
            {
                instance ?: MovieTvShowRepository(remoteDataSource).apply { instance = this }
            }
    }

    override fun getMovies(): MutableLiveData<List<MoviesTvData>> {
        val movieResult = MutableLiveData<List<MoviesTvData>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadDataCallback {
            override fun onAllDataReceived(responseClass: ResponseClass) {
                Log.i("movies", responseClass.results.toString())
                val movieList = ArrayList<MoviesTvData>()
                for (result in responseClass.results) {
                    val movie = MoviesTvData(result.original_title,
                        result.poster_path,
                        result.overview,
                        result.vote_average,
                        result.vote_count,
                        result.original_language,
                        result.popularity)
                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
                EspressoIdlingResource.decrement()
            }
        })
        return movieResult
    }

    override fun getTvShows(): MutableLiveData<List<MoviesTvData>> {
        val tvShowResult = MutableLiveData<List<MoviesTvData>>()
        remoteDataSource.getTvShow(object : RemoteDataSource.LoadDataCallback {
            override fun onAllDataReceived(responseClass: ResponseClass) {
                val tvList = ArrayList<MoviesTvData>()
                Log.i("tvshows", responseClass.results.toString())
                for (result in responseClass.results) {
                    val tvShow = MoviesTvData(result.original_title,
                        result.poster_path,
                        result.overview,
                        result.vote_average,
                        result.vote_count,
                        result.original_language,
                        result.popularity)
                    tvList.add(tvShow)
                }
                tvShowResult.postValue(tvList)
                EspressoIdlingResource.decrement()
            }

        })
        return tvShowResult
    }
}