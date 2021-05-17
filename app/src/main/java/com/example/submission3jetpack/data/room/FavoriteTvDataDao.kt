package com.example.submission3jetpack.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteTvDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteTvData: FavoriteTvData)

    @Query("DELETE from favorite_tv WHERE original_title = :title")
    fun delete(title: String)

    @Query("SELECT * from favorite_tv ORDER BY id ASC")
    fun getAllFavorites(): LiveData<List<FavoriteTvData>>

    @Query("SELECT * from favorite_tv where original_title = :title")
    fun getFavorite(title: String): List<FavoriteTvData>?
}