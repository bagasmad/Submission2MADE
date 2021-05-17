package com.example.submission3jetpack.data.room

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteMovieDataDao: FavoriteMovieDataDao
    private val mFavoriteTvDataDao: FavoriteTvDataDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteMovieDataDao = db.favoriteMovieDataDao()
        mFavoriteTvDataDao = db.favoriteTvDataDao()
    }

    fun getAllFavoriteMovies(): LiveData<List<FavoriteMovieData>> = mFavoriteMovieDataDao.getAllFavorites()
    
    fun insertMovie(favoriteMovieData: FavoriteMovieData) {
        executorService.execute { mFavoriteMovieDataDao.insert(favoriteMovieData) }
    }

    fun deleteMovie(title: String) {
        executorService.execute { mFavoriteMovieDataDao.delete(title) }
    }

    fun getAllFavoriteTvs() = mFavoriteTvDataDao.getAllFavorites()

    fun insertTv(favoriteTvData: FavoriteTvData)
    {
        executorService.execute { mFavoriteTvDataDao.insert(favoriteTvData) }
    }

    fun deleteTv(title: String)
    {
        executorService.execute { mFavoriteTvDataDao.delete(title) }
    }

    fun getFavoriteTv(title: String) = mFavoriteTvDataDao.getFavorite(title)

    fun getFavoriteMovie(title: String) = mFavoriteMovieDataDao.getFavorite(title)

}