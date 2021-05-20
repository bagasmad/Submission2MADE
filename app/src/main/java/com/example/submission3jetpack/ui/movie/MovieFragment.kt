package com.example.submission3jetpack.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3jetpack.databinding.MovieFragmentBinding
import com.example.submission3jetpack.ui.viewmodels.MovieViewModel
import com.example.submission3jetpack.ui.viewmodels.ViewModelFactory
import com.example.submission3jetpack.vo.Status

class MovieFragment() : Fragment() {
    lateinit var movieFragmentBinding: MovieFragmentBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        movieFragmentBinding = MovieFragmentBinding.inflate(inflater)
        return movieFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)
        val movieAdapter = MovieAdapter()
        viewModel.moviesList.observe(viewLifecycleOwner,
            { movies ->
               if (movies!=null)
               {
                   when (movies.status)
                   {
                       Status.LOADING ->
                       {

                       }

                       Status.SUCCESS ->
                       {
                           movieAdapter.setMovie(movies.data)
                       }
                       Status.ERROR ->
                       {
                           Toast.makeText(context,"Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                       }
                   }
               }
            })

        with(movieFragmentBinding.recyclerViewMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

    }

}