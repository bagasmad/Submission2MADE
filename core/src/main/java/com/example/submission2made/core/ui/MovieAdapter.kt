package com.example.submission2made.core.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission2made.core.R
import com.example.submission2made.core.databinding.CardItemBinding
import com.example.submission2made.core.domain.model.Movie


class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovie = arrayListOf<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListMovie: List<Movie>?) {
        if (newListMovie == null) return
        listMovie.clear()
        listMovie.addAll(newListMovie)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val data = listMovie[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMoviesBinding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(itemsMoviesBinding)
    }


    inner class MovieViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                movieTitle.text = movie.original_title
                movieDescription.text = movie.overview
                itemView.setOnClickListener {
                    onItemClick?.invoke(listMovie[adapterPosition])
                }
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.img_url, movie.poster_path))
                    .into(binding.posterImage)
            }
        }
    }


}