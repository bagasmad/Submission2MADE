package com.example.submission2made.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Entity(tableName = "movies_data")
@Parcelize
data class MovieEntity
    (

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @field: SerializedName("original_name", alternate = ["original_title"])
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
    var favorite: Boolean = false,
) : Parcelable