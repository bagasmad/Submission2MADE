package com.example.submission3jetpack.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.submission3jetpack.R
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.data.room.FavoriteMovieData
import com.example.submission3jetpack.data.room.FavoriteTvData
import com.example.submission3jetpack.databinding.ActivityDetailBinding
import com.example.submission3jetpack.ui.viewmodels.DetailViewModel
import com.example.submission3jetpack.ui.viewmodels.FavoriteMovieViewModel
import com.example.submission3jetpack.ui.viewmodels.FavoriteTvShowViewModel
import com.example.submission3jetpack.ui.viewmodels.FavoriteViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var data: MoviesTvData
    private lateinit var favoriteTvShowViewModel: FavoriteTvShowViewModel
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel
    private var flag = 0

    companion object {
        const val EXTRA_DATA = "data"
        const val FLAG = "flag"
        private var favoriteCheck = MutableLiveData<Boolean>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        data = intent.getParcelableExtra<MoviesTvData>(EXTRA_DATA) as MoviesTvData
        viewModel.setData(data)
        binding.textOverview.text = viewModel.getData().overview
        binding.titleText.text = viewModel.getData().original_title
        binding.originalLanguage.text =
            resources.getString(R.string.original_language, viewModel.getData().original_language)
        binding.textPopularity.text =
            resources.getString(R.string.popularity, viewModel.getData().popularity)
        binding.textRating.text = resources.getString(
            R.string.rating,
            viewModel.getData().vote_average,
            viewModel.getData().vote_count
        )
        Glide.with(applicationContext)
            .load(resources.getString(R.string.img_url, viewModel.getData().poster_path))
            .into(binding.imagePoster)
        flag = intent.getIntExtra("flag", 0)
        if(flag == 0)
        {
            favoriteMovieViewModel = FavoriteViewModelFactory.getInstance(application)
                .create(FavoriteMovieViewModel::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val movie = favoriteMovieViewModel.getFavorite(data.original_title)
                if (movie.isNullOrEmpty())
                {
                    favoriteCheck.postValue(false)
                }
                else
                {
                    favoriteCheck.postValue(true)
                }
            }
        }
        else
        {
            favoriteTvShowViewModel = FavoriteViewModelFactory.getInstance(application)
                .create(FavoriteTvShowViewModel::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val tv = favoriteTvShowViewModel.getFavorite(data.original_title)
                if (tv.isNullOrEmpty())
                {
                    favoriteCheck.postValue(false)
                }
                else
                {
                    favoriteCheck.postValue(true)
                }
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.id.favorite_button)?.isVisible = true
        menu?.findItem(R.id.favorite_menu)?.isVisible = false
        favoriteCheck.observe(this,
            {
                if(it)
                {
                    menu?.findItem(R.id.favorite_button)?.icon = getDrawable(R.drawable.ic_favorite)

                }
                else
                {
                    menu?.findItem(R.id.favorite_button)?.icon = getDrawable(R.drawable.ic_baseline_favorite_border_24)
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
                if(favoriteCheck.value==true)
                {
                    favoriteCheck.postValue(false)
                    deleteFavorite()
                }
                else
                {
                    favoriteCheck.postValue(true)
                    addToFavorite()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun addToFavorite()
    {
        if (flag == 0) {
            CoroutineScope(Dispatchers.IO).launch {
                favoriteMovieViewModel.addFavorite(FavoriteMovieData(0,
                    data.original_title,
                    data.poster_path,
                    data.overview,
                    data.vote_average,
                    data.vote_count,
                    data.original_language,
                    data.popularity))
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                favoriteTvShowViewModel.addFavorite(FavoriteTvData(0,
                    data.original_title,
                    data.poster_path,
                    data.overview,
                    data.vote_average,
                    data.vote_count,
                    data.original_language,
                    data.popularity))
            }
        }
    }

    fun deleteFavorite()
    {
        if (flag == 0) {
            CoroutineScope(Dispatchers.IO).launch {
                favoriteMovieViewModel.deleteFavorite(data.original_title)
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                favoriteTvShowViewModel.deleteFavorite(data.original_title)
            }
        }
    }
}