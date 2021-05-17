package com.example.submission3jetpack.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission3jetpack.databinding.TvShowFragmentBinding
import com.example.submission3jetpack.ui.movie.TvShowAdapter
import com.example.submission3jetpack.ui.viewmodels.TvShowViewModel
import com.example.submission3jetpack.ui.viewmodels.ViewModelFactory

class TvShowFragment() : Fragment() {
    private lateinit var tvShowFragmentBinding: TvShowFragmentBinding

    private lateinit var viewModelTv: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tvShowFragmentBinding = TvShowFragmentBinding.inflate(layoutInflater)
        return tvShowFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ViewModelFactory.getInstance()
        viewModelTv = ViewModelProvider(this, factory).get(TvShowViewModel::class.java)
        val tvShowAdapter = TvShowAdapter()
        viewModelTv.tvShows.observe(viewLifecycleOwner,
            { tvShows ->
                tvShowAdapter.setTvShow(tvShows)
            })

        with(tvShowFragmentBinding.recyclerViewTv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }
}