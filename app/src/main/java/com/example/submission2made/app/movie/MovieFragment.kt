package com.example.submission2made.app.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2made.app.detail.DetailActivity
import com.example.submission2made.core.ui.MovieAdapter
import com.example.submission2made.core.vo.Status
import com.example.submission2made.databinding.MovieFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private lateinit var movieFragmentBinding: MovieFragmentBinding
    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        movieFragmentBinding = MovieFragmentBinding.inflate(inflater)
        return movieFragmentBinding.root
    }

    override fun onResume() {
        val movieAdapter = MovieAdapter()
        movieAdapter.onItemClick =
            {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                intent.putExtra(DetailActivity.FLAG, 0)
                context?.startActivity(intent)
            }

        movieFragmentBinding.recyclerViewMovie.layoutManager = LinearLayoutManager(context)
        movieFragmentBinding.recyclerViewMovie.adapter = movieAdapter
        viewModel.getMovies().observe(viewLifecycleOwner,
            { movies ->
                if (movies != null) {
                    when (movies.status) {
                        Status.LOADING -> {
                            viewModel.isLoading.postValue(true)
                        }

                        Status.SUCCESS -> {
                            movieAdapter.setData(movies.data)
                            viewModel.isLoading.postValue(false)
                            Log.i("MoviesList", movies.data.toString())
                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })

        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

    }

}