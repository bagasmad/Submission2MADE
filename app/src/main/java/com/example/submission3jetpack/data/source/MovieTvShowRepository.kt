package com.example.submission3jetpack.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData
import com.example.submission3jetpack.data.source.local.LocalDataSource
import com.example.submission3jetpack.data.source.remote.ApiResponse
import com.example.submission3jetpack.data.source.remote.RemoteDataSource
import com.example.submission3jetpack.data.source.remote.response.MoviesTvDataResponse
import com.example.submission3jetpack.retrofit.ResponseClass
import com.example.submission3jetpack.utils.AppExecutors
import com.example.submission3jetpack.utils.EspressoIdlingResource
import com.example.submission3jetpack.vo.Resource

class MovieTvShowRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) :
    MovieTvShowDataSource {
    companion object {
        @Volatile
        private var instance: MovieTvShowRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieTvShowRepository =
            instance ?: synchronized(this)
            {
                instance ?: MovieTvShowRepository(remoteDataSource,localDataSource,appExecutors).apply { instance = this }
            }
    }

    override fun getMovies(): LiveData<Resource<List<MoviesData>>> {
        return object : NetworkBoundResource<List<MoviesData>, List<MoviesTvDataResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MoviesData>> =
                localDataSource.getMovies()

            override fun shouldFetch(data: List<MoviesData>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MoviesTvDataResponse>>> =
               remoteDataSource.getMovies()

            override fun saveCallResult(data: List<MoviesTvDataResponse>) {
                val moviesList = ArrayList<MoviesData>()
                for(movies in data)
                {
                 val movie = MoviesData(0, movies.original_title, movies.poster_path, movies.overview, movies.vote_average, movies.vote_count, movies.original_language, movies.popularity)
                moviesList.add(movie)
                }
                localDataSource.insertMovies(moviesList)
            }
        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<TvData>>> {
        return object : NetworkBoundResource<List<TvData>, List<MoviesTvDataResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvData>> =
                localDataSource.getTvShows()


            override fun shouldFetch(data: List<TvData>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<MoviesTvDataResponse>>> =
                remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<MoviesTvDataResponse>) {
                val tvList = ArrayList<TvData>()
                for(tvs in data)
                {
                    val tv = TvData(0, tvs.original_title, tvs.poster_path, tvs.overview, tvs.vote_average, tvs.vote_count, tvs.original_language, tvs.popularity)
                    tvList.add(tv)
                }
                localDataSource.insertTvShows(tvList)
            }
        }.asLiveData()
    }

    override fun updateFavoriteTvShow(tvData: TvData, state: Boolean) {
        appExecutors.diskIO().execute{localDataSource.updateFavoriteTvShow(tvData,state)}
    }

    override fun updateFavoriteMovie(moviesData: MoviesData, state: Boolean) {
        appExecutors.diskIO().execute{localDataSource.updateFavoriteMovie(moviesData,state)}
    }

    override fun getFavoriteMovies(): LiveData<List<MoviesData>> {
        return localDataSource.getFavoriteMovies()
    }

    override fun getFavoriteTvShows(): LiveData<List<TvData>> {
        return localDataSource.getFavoriteTvShows()

    }
}