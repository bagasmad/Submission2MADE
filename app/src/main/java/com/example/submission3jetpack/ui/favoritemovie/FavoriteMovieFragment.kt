package com.example.submission3jetpack.ui.favoritemovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.databinding.MovieFragmentBinding
import com.example.submission3jetpack.ui.movie.MovieAdapter
import com.example.submission3jetpack.ui.viewmodels.FavoriteMovieViewModel
import com.example.submission3jetpack.ui.viewmodels.FavoriteViewModelFactory
import com.example.submission3jetpack.ui.viewmodels.MovieViewModel
import com.example.submission3jetpack.ui.viewmodels.ViewModelFactory

class FavoriteMovieFragment: Fragment() {
    lateinit var movieFragmentBinding: MovieFragmentBinding

    private lateinit var viewModel: FavoriteMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        movieFragmentBinding = MovieFragmentBinding.inflate(inflater)
        return movieFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = FavoriteViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(FavoriteMovieViewModel::class.java)
        val movieAdapter = MovieAdapter()
        viewModel.getAllFavorite().observe(viewLifecycleOwner,
            { moviesFromDB ->
                val movies : ArrayList<MoviesTvData> = arrayListOf()
                moviesFromDB.forEach { favoriteMovieData ->
                    val movie = MoviesTvData(favoriteMovieData.original_title,favoriteMovieData.poster_path,favoriteMovieData.overview,favoriteMovieData.vote_average,favoriteMovieData.vote_count,favoriteMovieData.original_language, favoriteMovieData.popularity)
                    movies.add(movie)
                }
                movieAdapter.setMovie(movies)
            })

        with(movieFragmentBinding.recyclerViewMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }

}