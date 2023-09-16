package com.blank.movie.home.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.home.databinding.ItemMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import java.text.SimpleDateFormat
import java.util.Locale


class MovieAdapter : PagingDataAdapter<
        ResultMovieModel,
        MovieAdapter.MovieViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ResultMovieModel>() {
            override fun areItemsTheSame(
                oldItem: ResultMovieModel,
                newItem: ResultMovieModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ResultMovieModel,
                newItem: ResultMovieModel
            ): Boolean =
                oldItem == newItem
        }

        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }


    class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: ResultMovieModel) = with(binding) {


            Glide.with(this.root)
                .load(BASE_URL_IMAGE + data.posterPath)
                .placeholder(ColorDrawable(Color.BLACK))
                .centerCrop()
                .transition(withCrossFade())
                .into(imageItem)

            textMovieName.text = data.title

            val date = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(data.releaseDate)
            textDateRelease.text = SimpleDateFormat("MMM dd,yyyy", Locale.getDefault()).format(date)
        }
    }
}
