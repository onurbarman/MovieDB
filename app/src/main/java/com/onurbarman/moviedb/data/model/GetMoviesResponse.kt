package com.onurbarman.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse (

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val movies: List<Movies>,

    @SerializedName("total_pages")
    val pages: Int

)