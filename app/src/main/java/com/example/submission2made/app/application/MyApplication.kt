package com.example.submission2made.app.application

import android.app.Application
import com.example.submission2made.app.di.useCaseModule
import com.example.submission2made.app.di.viewModelModule
import com.example.submission2made.core.di.databaseModule
import com.example.submission2made.core.di.networkModule
import com.example.submission2made.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}