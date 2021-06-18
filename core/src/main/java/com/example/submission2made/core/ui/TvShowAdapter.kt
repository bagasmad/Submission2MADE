package com.example.submission2made.core.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission2made.core.R
import com.example.submission2made.core.databinding.CardItemBinding
import com.example.submission2made.core.domain.model.Tv

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTv = arrayListOf<Tv>()
    var onItemClick: ((Tv) -> Unit)? = null


    fun setData(newListTv: List<Tv>?) {
        if (newListTv == null) return
        listTv.clear()
        listTv.addAll(newListTv)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = listTv.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val data = listTv[position]
        holder.bind(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsTvShowBinding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvShowViewHolder(itemsTvShowBinding)
    }

    inner class TvShowViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: Tv) {
            with(binding) {
                movieTitle.text = tvShow.original_title
                movieDescription.text = tvShow.overview

                itemView.setOnClickListener {
                    onItemClick?.invoke(listTv[adapterPosition])
                }
                Glide.with(itemView.context)
                    .load(itemView.resources.getString(R.string.img_url, tvShow.poster_path))
                    .into(binding.posterImage)
            }
        }
    }


}