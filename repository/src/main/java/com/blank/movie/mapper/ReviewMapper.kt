package com.blank.movie.mapper

import com.blank.movie.data.model.ReviewResultResponse
import com.blank.movie.domain.model.ReviewModel
import com.blank.movie.helper.DomainMapper
import javax.inject.Inject

class ReviewMapper @Inject constructor() : DomainMapper<ReviewResultResponse, ReviewModel> {

    override fun mapToDomainModel(dataModel: ReviewResultResponse): ReviewModel {
        return with(dataModel) {
            ReviewModel(
                id = id.orEmpty(),
                urlImage = authorDetails?.avatarPath.orEmpty(),
                authorName = author.orEmpty(),
                review = content.orEmpty(),
                rating = authorDetails?.rating ?: 0
            )
        }
    }
}