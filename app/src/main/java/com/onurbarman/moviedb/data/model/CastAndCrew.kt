package com.onurbarman.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class CastAndCrew(

    @SerializedName("id")
    val id: Int,

    @SerializedName("cast")
    val cast: List<Cast>,

    @SerializedName("crew")
    val crew: List<Crew>
)

{
    data class Crew(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("profile_path")
        val profile_path: Any
    )

    data class Cast(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("profile_path")
        val profile_path: String
    )
}