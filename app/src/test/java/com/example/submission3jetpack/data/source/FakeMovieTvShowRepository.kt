package com.example.submission3jetpack.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.data.source.remote.RemoteDataSource
import com.example.submission3jetpack.retrofit.ResponseClass

class FakeMovieTvShowRepository (private val remoteDataSource: RemoteDataSource) : MovieTvShowDataSource {

    override fun getMovies(): LiveData<List<MoviesTvData>> {
        val movieResult = MutableLiveData<List<MoviesTvData>>()
        remoteDataSource.getMovies(object : RemoteDataSource.LoadDataCallback
        {
            override fun onAllDataReceived(responseClass: ResponseClass) {
                val movieList = ArrayList<MoviesTvData>()
                for(result in responseClass.results)
                {
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
            }
        })
        return movieResult
    }

    override fun getTvShows(): LiveData<List<MoviesTvData>> {
        val tvShowResult = MutableLiveData<List<MoviesTvData>>()
        remoteDataSource.getTvShow(object : RemoteDataSource.LoadDataCallback{
            override fun onAllDataReceived(responseClass: ResponseClass) {
                val tvList = ArrayList<MoviesTvData>()
                for(result in responseClass.results)
                {
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
            }

        })
        return tvShowResult
    }
}