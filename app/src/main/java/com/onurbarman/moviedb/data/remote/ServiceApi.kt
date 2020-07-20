package com.onurbarman.moviedb.data.remote

import com.onurbarman.moviedb.app.Constants
import com.onurbarman.moviedb.data.model.GetMoviesResponse
import com.onurbarman.moviedb.data.model.GetTVResponse
import com.onurbarman.moviedb.data.model.CastAndCrew
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("page") page: Int ): Call<GetMoviesResponse>

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("page") page: Int ): Call<GetMoviesResponse>


    @GET("tv/top_rated")
    fun getTVTopRated(
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("page") page: Int ): Call<GetTVResponse>

    @GET("tv/popular")
    fun getTVPopular(
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("page") page: Int ): Call<GetTVResponse>

    @GET("tv/{tv_id}/credits")
    fun getTVDetailCredits(
        @Path("tv_id") tv_id : String,
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("page") page: Int ): Call<CastAndCrew>

    @GET("movie/{movie_id}/credits")
    fun getMovieDetailCredits(
        @Path("movie_id") movie_id : String,
        @Query("api_key") apiKey: String = Constants.apiKey,
        @Query("page") page: Int ): Call<CastAndCrew>

}