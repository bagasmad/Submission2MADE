package com.example.submission3jetpack.ui.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission3jetpack.R
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.data.TvData
import com.example.submission3jetpack.databinding.ActivityDetailBinding
import com.example.submission3jetpack.ui.viewmodels.DetailViewModel
import com.example.submission3jetpack.ui.viewmodels.ViewModelFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataMovie: MoviesData
    private lateinit var dataTv: TvData
    private lateinit var viewModel: DetailViewModel

    private var nextBoolean = true
    private var flag = 0

    companion object {
        const val EXTRA_DATA = "data"
        const val FLAG = "flag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val factory = ViewModelFactory.getInstance(application)
        viewModel = ViewModelProvider(this, factory).get(DetailViewModel::class.java)
        checkMovieOrTv()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.favorite_button)?.isVisible = true
        menu?.findItem(R.id.favorite_menu)?.isVisible = false
        viewModel.favoriteCheck.observe(this,
            {
                if (it) {
                    menu?.findItem(R.id.favorite_button)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite)
                    nextBoolean = false

                } else {
                    menu?.findItem(R.id.favorite_button)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
                    nextBoolean = true
                }
            })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.favorite_button -> {
                if (flag == 0) {
                    viewModel.updateFavoriteMovie(dataMovie, nextBoolean)
                    viewModel.favoriteCheck.postValue(nextBoolean)
                } else {
                    viewModel.updateFavoriteTvShow(dataTv, nextBoolean)
                    viewModel.favoriteCheck.postValue(nextBoolean)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkMovieOrTv() {
        flag = intent.getIntExtra("flag", 0)
        if (flag == 0) {
            dataMovie = intent.getParcelableExtra<MoviesData>(EXTRA_DATA) as MoviesData
            viewModel.setMovieData(dataMovie)
            setViews(viewModel.getMovieData().overview,
                viewModel.getMovieData().original_title,
                viewModel.getMovieData().original_language,
                viewModel.getMovieData().popularity,
                viewModel.getMovieData().vote_average,
                viewModel.getMovieData().vote_count,
                viewModel.getMovieData().poster_path)
            viewModel.favoriteCheck.postValue(viewModel.getMovieData().favorite)
        } else {
            dataTv = intent.getParcelableExtra<TvData>(EXTRA_DATA) as TvData
            viewModel.setTvData(dataTv)
            setViews(viewModel.getTvData().overview,
                viewModel.getTvData().original_title,
                viewModel.getTvData().original_language,
                viewModel.getTvData().popularity,
                viewModel.getTvData().vote_average,
                viewModel.getTvData().vote_count,
                viewModel.getTvData().poster_path)
            viewModel.favoriteCheck.postValue(viewModel.getTvData().favorite)
        }
    }

    private fun setViews(
        overview: String,
        originalTitle: String,
        originalLanguage: String,
        popularity: Double,
        voteAverage: Double,
        voteCount: Int,
        posterPath: String,
    ) {
        binding.textOverview.text = overview
        binding.titleText.text = originalTitle
        binding.originalLanguage.text =
            resources.getString(R.string.original_language,
                originalLanguage)
        binding.textPopularity.text =
            resources.getString(R.string.popularity, popularity)
        binding.textRating.text = resources.getString(
            R.string.rating,
            voteAverage,
            voteCount
        )
        Glide.with(applicationContext)
            .load(resources.getString(R.string.img_url, posterPath))
            .into(binding.imagePoster)
    }

}