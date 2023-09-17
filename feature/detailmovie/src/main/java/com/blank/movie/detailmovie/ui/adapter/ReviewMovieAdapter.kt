package com.blank.movie.detailmovie.ui.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blank.movie.detailmovie.databinding.ItemReviewBinding
import com.blank.movie.domain.model.ReviewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade


class ReviewMovieAdapter : PagingDataAdapter<
        ReviewModel,
        ReviewMovieAdapter.ReviewMovieViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ReviewModel>() {
            override fun areItemsTheSame(
                oldItem: ReviewModel,
                newItem: ReviewModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: ReviewModel,
                newItem: ReviewModel
            ): Boolean =
                oldItem == newItem
        }

        private const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewMovieViewHolder {
        return ReviewMovieViewHolder(
            ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewMovieViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }


    class ReviewMovieViewHolder(private val binding: ItemReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: ReviewModel) = with(binding) {

            if (data.urlImage.isNotEmpty()) {
                Glide.with(this.root)
                    .load(BASE_URL_IMAGE + data.urlImage)
                    .placeholder(ColorDrawable(Color.BLACK))
                    .circleCrop()
                    .transition(withCrossFade())
                    .into(ivProfile)
            }

            textContent.text = data.review
            textScoreItem.text = data.rating.toString()
            val title = "A Review By ${data.authorName}"
            textTitle.text = title
        }
    }
}
