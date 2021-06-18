package com.example.submission2made.app.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2made.app.detail.DetailActivity
import com.example.submission2made.core.ui.TvShowAdapter
import com.example.submission2made.core.vo.Status
import com.example.submission2made.databinding.TvShowFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private lateinit var tvShowFragmentBinding: TvShowFragmentBinding
    private val viewModelTv: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        tvShowFragmentBinding = TvShowFragmentBinding.inflate(layoutInflater)
        return tvShowFragmentBinding.root
    }

    override fun onResume() {
        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.onItemClick =
            {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                intent.putExtra(DetailActivity.FLAG, 1)
                context?.startActivity(intent)
            }
        viewModelTv.getTvShows().observe(viewLifecycleOwner,
            { tvShows ->
                (if (tvShows != null) {
                    when (tvShows.status) {
                        Status.SUCCESS -> {

                            tvShowAdapter.setData(tvShows.data)
                            viewModelTv.isLoading.postValue(false)


                        }
                        Status.LOADING -> {
                            viewModelTv.isLoading.postValue(true)

                        }
                        Status.ERROR -> {
                            Toast.makeText(context, "Terjadi Kesalahan", Toast.LENGTH_LONG).show()
                        }
                    }
                })
            })

        with(tvShowFragmentBinding.recyclerViewTv) {
            layoutManager = LinearLayoutManager(context)
            adapter = tvShowAdapter
        }
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
    }
}