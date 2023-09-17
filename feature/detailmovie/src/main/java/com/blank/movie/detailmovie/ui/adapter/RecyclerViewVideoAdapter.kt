package com.blank.movie.detailmovie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blank.movie.detailmovie.databinding.ItemVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class RecyclerViewVideoAdapter :
    ListAdapter<String, RecyclerViewVideoAdapter.ViewHolderVideo>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return (oldItem == newItem)
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
                oldItem == newItem
        }
    }

    class ViewHolderVideo(private val itemVideo: ItemVideoBinding) :
        RecyclerView.ViewHolder(itemVideo.root) {
        fun bind(id: String) {
            itemVideo.youtubePlayerView.apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(id, 0F)
                    }
                })
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVideo {
        return ViewHolderVideo(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderVideo, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}