package com.blank.movie.data.model.detailmovie

import com.google.gson.annotations.SerializedName

data class GenreResponse(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null

)