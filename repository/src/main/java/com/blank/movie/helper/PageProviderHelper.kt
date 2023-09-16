package com.blank.movie.helper

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.RemoteMediator
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PagerProviderHelper @Inject constructor() {

    fun <K : Any, V : Any> createPager(
        config: PagingConfig,
        mediator: RemoteMediator<K, V>? = null,
        pagingSourceFactory: () -> PagingSource<K, V>
    ): Pager<K, V> {
        return Pager(
            config = config,
            remoteMediator = mediator,
            pagingSourceFactory = pagingSourceFactory
        )
    }
}