package com.blank.movie.helper

import com.blank.movie.data.model.ErrorResponse
import com.blank.movie.data.model.NetworkResponse
import com.blank.movie.domain.model.DomainResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.HttpURLConnection

internal fun <F : Any, T : Any> NetworkResponse<F, ErrorResponse>.mapToDomainResource(
    transformSuccess: DomainMapper<F, T>?,
): DomainResource<T> {
    return when (this) {
        is NetworkResponse.ErrorApi -> convertToDomainError()
        is NetworkResponse.ErrorNetwork -> DomainResource.Error(
            error, error.message.orEmpty()
        )

        is NetworkResponse.ErrorUnknown -> DomainResource.Error(
            error = null,
            message = error?.message.orEmpty()
        )

        is NetworkResponse.Success -> {
            if (transformSuccess == null) DomainResource.SuccessNoData("")
            else DomainResource.Success(
                data = transformSuccess.mapToDomainModel(data),
            )
        }

        is NetworkResponse.SuccessNoData -> DomainResource.SuccessNoData(message)
    }
}


fun <T : Any> NetworkResponse.ErrorApi<T, ErrorResponse>.convertToDomainError(): DomainResource.Error =
    when (code) {
        HttpURLConnection.HTTP_INTERNAL_ERROR,
        HttpURLConnection.HTTP_BAD_GATEWAY,
        HttpURLConnection.HTTP_GATEWAY_TIMEOUT,
        -> DomainResource.Error(
            error = null,
            message = "Server Error"
        )

        HttpURLConnection.HTTP_UNAVAILABLE -> DomainResource.Error(
            error = null,
            message = "Server Error"
        )

        else -> DomainResource.Error(
            error = null,
            message = "Error Tidak di ketahui"
        )
    }


fun <D : Any, T : Any> dataSourceHandling(
    callApi: suspend () -> NetworkResponse<D, ErrorResponse>,
    mapper: DomainMapper<D, T>? = null,
): Flow<DomainResource<T>> = flow {
    emit(DomainResource.Loading)
    val result: NetworkResponse<D, ErrorResponse> = callApi.invoke()
    emit(result.mapToDomainResource(mapper))
}





