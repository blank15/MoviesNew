package com.blank.movie.helper

import androidx.paging.Pager
import androidx.paging.PagingConfig


fun <V : Any> createPager(
    config: PagingConfig,
    resourceResult: suspend (Int) -> Pair<Throwable?, List<V>>
): Pager<Int, V> {
    return Pager(
        config = config,
        pagingSourceFactory = {
            PagingSourceHelper(resourceResult)
        }
    )
}
