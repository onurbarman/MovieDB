package com.onurbarman.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class Movies(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("vote_count")
    val vote_count: Int,

    @SerializedName("vote_average")
    val rating: Double,

    @SerializedName("release_date")
    val releaseDate: String
)
