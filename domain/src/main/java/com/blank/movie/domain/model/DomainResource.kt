package com.blank.movie.domain.model

sealed class DomainResource<out Data> {
    data class Success<Data>(
        val data: Data,
        val message: String? = null
    ) : DomainResource<Data>()

    data class SuccessNoData(
        val message: String
    ) : DomainResource<Nothing>()

    data class Error(
        var error: Exception
    ) : DomainResource<Nothing>()
}
