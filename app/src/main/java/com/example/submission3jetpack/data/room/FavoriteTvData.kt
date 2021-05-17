package com.example.submission3jetpack.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_tv")
@Parcelize
data class FavoriteTvData(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int = 0,

    @ColumnInfo(name = "original_title")
    val original_title: String,

    @ColumnInfo(name = "poster_path")
    val poster_path: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "vote_average")
    val vote_average: Double,

    @ColumnInfo(name = "vote_count")
    val vote_count: Int,

    @ColumnInfo(name = "original_language")
    val original_language: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "favorite")
    val favorite: Boolean = true
) : Parcelable