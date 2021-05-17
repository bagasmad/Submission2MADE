package com.example.submission3jetpack.ui.movie


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission3jetpack.R
import com.example.submission3jetpack.data.MoviesTvData
import com.example.submission3jetpack.databinding.CardItemBinding
import com.example.submission3jetpack.ui.detail.DetailActivity


class TvShowAdapter() : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private var listTvShow = ArrayList<MoviesTvData>()

    fun setTvShow(movies: List<MoviesTvData>?) {
        if (movies == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvShowViewHolder(itemsTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val course = listTvShow[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listTvShow.size


    class TvShowViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: MoviesTvData) {
            with(binding) {
                movieTitle.text = tvShow.original_title
                movieDescription.text = tvShow.overview

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_DATA, tvShow)
                    intent.putExtra(DetailActivity.FLAG, 1)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.img_url, tvShow.poster_path))
                    .into(binding.posterImage)
            }
        }
    }


}