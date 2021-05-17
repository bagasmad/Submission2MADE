package com.example.submission3jetpack.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteMovieDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteMovieData: FavoriteMovieData)

    @Query("DELETE from favorite_movie WHERE original_title = :title")
    fun delete(title: String)

    @Query("SELECT * from favorite_movie ORDER BY id ASC")
    fun getAllFavorites(): LiveData<List<FavoriteMovieData>>

    @Query("SELECT * from favorite_movie where original_title = :title")
    fun getFavorite(title: String): List<FavoriteMovieData>?
}