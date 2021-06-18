package com.example.submission2made.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { com.example.submission2made.favorite.favoritetvshow.FavoriteTvShowViewModel(get()) }
    viewModel { com.example.submission2made.favorite.favoritemovie.FavoriteMovieViewModel(get()) }
}
