package com.onurbarman.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class GetTVResponse (

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val movies: List<TV>,

    @SerializedName("total_pages")
    val pages: Int

)