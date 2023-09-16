package com.blank.movie.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blank.movie.home.R
import com.blank.movie.home.databinding.ItemPagingLoadMoreBinding

class PagingLoadStateAdapter(
    private val retryCallback: () -> Unit
) : LoadStateAdapter<PagingLoadStateAdapter.MoviesNetworkStateViewHolder>() {
    override fun onBindViewHolder(holder: MoviesNetworkStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): MoviesNetworkStateViewHolder {
        return MoviesNetworkStateViewHolder(parent, retryCallback)
    }

    class MoviesNetworkStateViewHolder(
        parent: ViewGroup,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_paging_load_more, parent, false)
    ) {
        private val binding = ItemPagingLoadMoreBinding.bind(itemView)
        private val loading = binding.pbRowLoadMore
        private val retry = binding.llParentClickableReload
            .also {
                it.setOnClickListener { retryCallback() }
            }

        fun bindTo(loadState: LoadState) {
            loading.isVisible = loadState is LoadState.Loading
            retry.isVisible = loadState is LoadState.Error
            binding.vfRowLoadMore.displayedChild =
                if (loadState is LoadState.Error) VIEW_FLIPPER_ERROR else VIEW_FLIPPER_NORMAL
        }
    }

    companion object {
        const val VIEW_FLIPPER_ERROR = 1
        const val VIEW_FLIPPER_NORMAL = 0
    }
}
