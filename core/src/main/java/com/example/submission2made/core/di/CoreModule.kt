package com.example.submission2made.core.di

import androidx.room.Room
import com.example.submission2made.core.data.source.MovieTvShowRepository
import com.example.submission2made.core.data.source.local.LocalDataSource
import com.example.submission2made.core.data.source.local.room.DatabaseMovieTv
import com.example.submission2made.core.data.source.remote.RemoteDataSource
import com.example.submission2made.core.domain.repository.IRepository
import com.example.submission2made.core.retrofit.RetrofitInterface
import com.example.submission2made.core.utils.AppExecutors
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<DatabaseMovieTv>().MovieTvDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            DatabaseMovieTv::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single { OkHttpClient.Builder().build() }
    single {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .client(get())
            .build()
        retrofit.create(RetrofitInterface::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRepository> {
        MovieTvShowRepository(get(),
            get(),
            get())
    }
}