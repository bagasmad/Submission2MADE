package com.example.submission3jetpack.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData

@Database(entities = [MoviesData::class, TvData::class], version = 1)
abstract class DatabaseMovieTv : RoomDatabase() {
    abstract fun MovieTvDao(): MovieTvDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseMovieTv? = null

        fun getInstance(context: Context): DatabaseMovieTv =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseMovieTv::class.java,
                    "Movie db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}