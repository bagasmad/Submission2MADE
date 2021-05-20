package com.example.submission3jetpack.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData

@Dao
interface MovieTvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesData>)

    @Query("SELECT * from movies_data ORDER BY id ASC")
    fun getMovies(): LiveData<List<MoviesData>>

    @Query("SELECT * from movies_data WHERE favorite = 1")
    fun getFavoriteMovies(): LiveData<List<MoviesData>>

    @Query("SELECT * FROM movies_data WHERE original_title = :title")
    fun getMovieByTitle(title: String): LiveData<MoviesData>

    @Update
    fun updateMovie(movie: MoviesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvData>)

    @Query("SELECT * from tv_data ORDER BY id ASC")
    fun getTvShows(): LiveData<List<TvData>>

    @Query("SELECT * from tv_data WHERE favorite = 1")
    fun getFavoriteTvShows(): LiveData<List<TvData>>

    @Query("SELECT * FROM tv_data WHERE original_title = :title")
    fun getTvByTitle(title: String): LiveData<TvData>

    @Update
    fun updateTvShow(tvShow: TvData)

}