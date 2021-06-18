package com.example.submission2made.core.data.source.local.room

import androidx.room.*
import com.example.submission2made.core.data.source.local.entity.MovieEntity
import com.example.submission2made.core.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTvDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("SELECT * from movies_data ")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * from movies_data WHERE favorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Update
    fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: List<TvEntity>)

    @Query("SELECT * from tv_data ")
    fun getTvShows(): Flow<List<TvEntity>>

    @Query("SELECT * from tv_data WHERE favorite = 1")
    fun getFavoriteTvShows(): Flow<List<TvEntity>>

    @Update
    fun updateTvShow(tvShow: TvEntity)

}