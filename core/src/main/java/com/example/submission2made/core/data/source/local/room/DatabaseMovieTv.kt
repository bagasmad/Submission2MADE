package com.example.submission2made.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.submission2made.core.data.source.local.entity.MovieEntity
import com.example.submission2made.core.data.source.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1)
abstract class DatabaseMovieTv : RoomDatabase() {
    abstract fun MovieTvDao(): MovieTvDao
}