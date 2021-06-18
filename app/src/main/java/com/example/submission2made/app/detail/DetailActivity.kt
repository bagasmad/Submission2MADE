package com.example.submission2made.app.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import com.bumptech.glide.Glide
import com.example.submission2made.R
import com.example.submission2made.core.domain.model.Movie
import com.example.submission2made.core.domain.model.Tv
import com.example.submission2made.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var dataMovie: Movie
    private lateinit var dataTv: Tv
    private val viewModel: DetailViewModel by viewModel()

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
        viewModel.isLoading.postValue(true)
        checkMovieOrTv()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.favorite_button)?.isVisible = true
        menu?.findItem(R.id.favorite_menu)?.isVisible = false
        viewModel.isLoading.observe(this,
            {
                if (it) {
                    binding.progressCircular.visibility = View.VISIBLE
                    binding.scrollView.visibility = View.GONE
                } else {
                    binding.progressCircular.visibility = View.GONE
                    binding.scrollView.visibility = View.VISIBLE

                }
            })
        viewModel.favoriteCheck.observe(this,
            {
                if (it) {
                    menu?.findItem(R.id.favorite_button)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite)
                    MenuItemCompat.setContentDescription(menu?.findItem(R.id.favorite_button),
                        "favorited")
                    nextBoolean = false

                } else {
                    menu?.findItem(R.id.favorite_button)?.icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24)
                    MenuItemCompat.setContentDescription(menu?.findItem(R.id.favorite_button),
                        "notfavorited")
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
            dataMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie
            viewModel.setMovieData(dataMovie)
            with(viewModel.getMovieData())
            {
                setViews(this.overview,
                    this.original_title,
                    this.original_language,
                    this.popularity,
                    this.vote_average,
                    this.vote_count,
                    this.poster_path)
                viewModel.favoriteCheck.postValue(this.favorite)
            }
        } else {
            dataTv = intent.getParcelableExtra<Tv>(EXTRA_DATA) as Tv
            viewModel.setTvData(dataTv)
            with(viewModel.getTvData())
            {
                setViews(this.overview,
                    this.original_title,
                    this.original_language,
                    this.popularity,
                    this.vote_average,
                    this.vote_count,
                    this.poster_path)
                viewModel.favoriteCheck.postValue(this.favorite)
            }
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
        binding.textOriginalLanguage.text =
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
        viewModel.isLoading.postValue(false)
    }

}