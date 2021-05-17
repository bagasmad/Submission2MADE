package com.example.submission3jetpack.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteMovieData::class, FavoriteTvData::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteMovieDataDao(): FavoriteMovieDataDao
    abstract fun favoriteTvDataDao() : FavoriteTvDataDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java)
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoriteDatabase::class.java, "favorite_database")
                        .build()
                }
            }
        return INSTANCE as FavoriteDatabase
        }
    }
}