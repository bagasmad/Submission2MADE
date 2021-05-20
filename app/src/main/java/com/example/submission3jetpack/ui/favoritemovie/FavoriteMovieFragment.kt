package com.example.submission3jetpack.ui.favoritemovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.databinding.MovieFragmentBinding
import com.example.submission3jetpack.ui.movie.MovieAdapter
import com.example.submission3jetpack.ui.viewmodels.FavoriteMovieViewModel
import com.example.submission3jetpack.ui.viewmodels.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
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
        val factory = ViewModelFactory.getInstance(requireActivity().application)
        viewModel = ViewModelProvider(this, factory).get(FavoriteMovieViewModel::class.java)
        val movieAdapter = MovieAdapter()
        viewModel.favoriteMoviesList.observe(viewLifecycleOwner,
            { favoriteMovies ->
                movieAdapter.setMovie(favoriteMovies)
            })

        with(movieFragmentBinding.recyclerViewMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }

}