package com.example.submission2made.favorite.favoritetvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2made.app.detail.DetailActivity
import com.example.submission2made.core.ui.TvShowAdapter
import com.example.submission2made.databinding.TvShowFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {
    private lateinit var tvShowFragmentBinding: TvShowFragmentBinding
    private val viewModelTv: FavoriteTvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tvShowFragmentBinding = TvShowFragmentBinding.inflate(layoutInflater)
        return tvShowFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.onItemClick =
            {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                intent.putExtra(DetailActivity.FLAG, 1)
                context?.startActivity(intent)
            }
        viewModelTv.isLoading.observe(viewLifecycleOwner,
            {
                if (it) {
                    tvShowFragmentBinding.progressCircular.visibility = View.VISIBLE
                    tvShowFragmentBinding.recyclerViewTv.visibility = View.GONE
                } else {
                    tvShowFragmentBinding.progressCircular.visibility = View.GONE
                    tvShowFragmentBinding.recyclerViewTv.visibility = View.VISIBLE

                }
            })
        viewModelTv.isLoading.postValue(true)
        viewModelTv.getFavoriteTvShows().observe(viewLifecycleOwner,
            { favoriteTvShow ->
                tvShowAdapter.setData(favoriteTvShow)
                viewModelTv.isLoading.postValue(false)
            })
        with(tvShowFragmentBinding.recyclerViewTv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            adapter = tvShowAdapter
        }
    }
}