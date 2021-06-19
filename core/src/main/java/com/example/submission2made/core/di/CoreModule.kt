package com.example.submission2made.core.di

import androidx.room.Room
import com.example.submission2made.core.data.source.MovieTvShowRepository
import com.example.submission2made.core.data.source.local.LocalDataSource
import com.example.submission2made.core.data.source.local.room.DatabaseMovieTv
import com.example.submission2made.core.data.source.remote.RemoteDataSource
import com.example.submission2made.core.domain.repository.IRepository
import com.example.submission2made.core.retrofit.RetrofitInterface
import com.example.submission2made.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val databaseModule = module {
    factory { get<DatabaseMovieTv>().MovieTvDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("submission2made".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            DatabaseMovieTv::class.java, "Movie.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
        OkHttpClient.Builder()
        .certificatePinner(certificatePinner).build() }
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