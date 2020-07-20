package com.onurbarman.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class TV(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("first_air_date")
    val first_air_date: String,

    @SerializedName("vote_count")
    val vote_count: Int,

    @SerializedName("vote_average")
    val vote_average: Double,

    @SerializedName("backdrop_path")
    val backdrop_path: String,

    @SerializedName("poster_path")
    val poster_path: String

    )