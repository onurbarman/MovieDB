package com.onurbarman.moviedb.data.repository.movies

import com.onurbarman.moviedb.util.Constants
import com.onurbarman.moviedb.data.model.CastAndCrew
import com.onurbarman.moviedb.data.model.GetMoviesResponse
import com.onurbarman.moviedb.data.model.Movies
import com.onurbarman.moviedb.data.remote.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MoviesRepositoryImpl : MoviesRepository {


    private val api: ServiceApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(ServiceApi::class.java)
    }

    override fun getNowPlayingMovies(page: Int, onSuccess: (movies: List<Movies>) -> Unit, onError: () -> Unit) {
        api.getNowPlayingMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    getResponse(response, onSuccess, onError)
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()

                }
            })
    }

    override fun getPopularMovies(page: Int, onSuccess: (movies: List<Movies>) -> Unit, onError: () -> Unit) {
        api.getPopularMovies(page = page)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onResponse(call: Call<GetMoviesResponse>, response: Response<GetMoviesResponse>) {
                    getResponse(response, onSuccess, onError)
                }

                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()

                }
            })
    }

    override fun getMovieDetailCredits(movie_id: String, page: Int, onSuccess: (castAndCrew: CastAndCrew) -> Unit, onError: () -> Unit) {
        api.getMovieDetailCredits(movie_id = movie_id, page = page)
            .enqueue(object : Callback<CastAndCrew> {

                override fun onResponse(call: Call<CastAndCrew>, response: Response<CastAndCrew>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            onSuccess.invoke(responseBody)
                        } else {
                            onError.invoke()
                        }
                    }
                    else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<CastAndCrew>, t: Throwable) {
                    onError.invoke()

                }
            })
    }


    private fun getResponse(response : Response<GetMoviesResponse>, onSuccess: (tv: List<Movies>)-> Unit, onError: () -> Unit)
    {
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                onSuccess.invoke(responseBody.movies)
            } else {
                onError.invoke()
            }
        }
        else {
            onError.invoke()
        }
    }
}