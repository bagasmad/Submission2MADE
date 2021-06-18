package com.example.submission2made.app.di

import com.example.submission2made.app.detail.DetailViewModel
import com.example.submission2made.app.movie.MovieViewModel
import com.example.submission2made.app.tvshow.TvShowViewModel
import com.example.submission2made.core.domain.usecase.Interactor
import com.example.submission2made.core.domain.usecase.UseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<UseCase> { Interactor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}