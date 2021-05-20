package com.example.submission3jetpack.ui.movie


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission3jetpack.R
import com.example.submission3jetpack.data.MoviesData
import com.example.submission3jetpack.databinding.CardItemBinding
import com.example.submission3jetpack.ui.detail.DetailActivity


class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MoviesData>()

    fun setMovie(movies: List<MoviesData>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMoviesBinding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val course = listMovies[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listMovies.size


    class MovieViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviesData) {
            with(binding) {
                movieTitle.text = movie.original_title
                movieDescription.text = movie.overview

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, movie)
                    intent.putExtra(DetailActivity.FLAG, 0)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.img_url, movie.poster_path))
                    .into(binding.posterImage)
            }
        }
    }


}