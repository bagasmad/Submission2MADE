package com.example.submission3jetpack.ui.favoritetvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.databinding.TvShowFragmentBinding
import com.example.submission3jetpack.ui.movie.TvShowAdapter
import com.example.submission3jetpack.ui.viewmodels.FavoriteTvShowViewModel
import com.example.submission3jetpack.ui.viewmodels.FavoriteViewModelFactory
import com.example.submission3jetpack.ui.viewmodels.TvShowViewModel
import com.example.submission3jetpack.ui.viewmodels.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {
    private lateinit var tvShowFragmentBinding: TvShowFragmentBinding


    private lateinit var viewModelTv: FavoriteTvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tvShowFragmentBinding = TvShowFragmentBinding.inflate(layoutInflater)
        return tvShowFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = FavoriteViewModelFactory.getInstance(requireActivity().application)
        viewModelTv = ViewModelProvider(this, factory).get(FavoriteTvShowViewModel::class.java)
        val tvShowAdapter = TvShowAdapter()
        viewModelTv.getAllFavorite().observe(viewLifecycleOwner,
            { tvShowsFromDB ->
                val tvShows : ArrayList<MoviesTvData> = arrayListOf()
                tvShowsFromDB.forEach { favoriteTvData ->
                    val tv = MoviesTvData(favoriteTvData.original_title,favoriteTvData.poster_path,favoriteTvData.overview,favoriteTvData.vote_average,favoriteTvData.vote_count,favoriteTvData.original_language, favoriteTvData.popularity)
                    tvShows.add(tv)
                }
                tvShowAdapter.setTvShow(tvShows)
            })
        with(tvShowFragmentBinding.recyclerViewTv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }
}