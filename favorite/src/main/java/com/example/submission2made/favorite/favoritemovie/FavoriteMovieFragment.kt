package com.example.submission2made.favorite.favoritemovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2made.app.detail.DetailActivity
import com.example.submission2made.core.ui.MovieAdapter
import com.example.submission2made.databinding.MovieFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {
    lateinit var movieFragmentBinding: MovieFragmentBinding
    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        movieFragmentBinding = MovieFragmentBinding.inflate(inflater)
        return movieFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick =
            {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                intent.putExtra(DetailActivity.FLAG, 0)
                context?.startActivity(intent)
            }
        viewModel.isLoading.observe(viewLifecycleOwner,
            {
                if (it) {
                    movieFragmentBinding.progressCircular.visibility = View.VISIBLE
                    movieFragmentBinding.recyclerViewMovie.visibility = View.GONE
                } else {
                    movieFragmentBinding.progressCircular.visibility = View.GONE
                    movieFragmentBinding.recyclerViewMovie.visibility = View.VISIBLE

                }
            })
        viewModel.isLoading.postValue(true)

        viewModel.getFavoriteMovies().observe(viewLifecycleOwner,
            { favoriteMovies ->
                movieAdapter.setData(favoriteMovies)
                viewModel.isLoading.postValue(false)
            })

        with(movieFragmentBinding.recyclerViewMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = movieAdapter
        }

    }

}