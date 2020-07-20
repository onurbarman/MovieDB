package com.onurbarman.moviedb.data.repository.tv

import com.onurbarman.moviedb.util.Constants
import com.onurbarman.moviedb.data.model.CastAndCrew
import com.onurbarman.moviedb.data.model.GetTVResponse
import com.onurbarman.moviedb.data.model.TV
import com.onurbarman.moviedb.data.remote.ServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TVRepositoryImpl : TVRepository {

    private val api: ServiceApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(ServiceApi::class.java)
    }

    override fun getTVTopRated(page: Int, onSuccess: (tv: List<TV>) -> Unit, onError: () -> Unit) {
        api.getTVTopRated(page = page)
            .enqueue(object : Callback<GetTVResponse> {
                override fun onResponse(call: Call<GetTVResponse>, response: Response<GetTVResponse>) {
                    getResponse(response, onSuccess, onError)
                }

                override fun onFailure(call: Call<GetTVResponse>, t: Throwable) {
                    onError.invoke()

                }
            })
    }

    override fun getTVPopular(page: Int, onSuccess: (tv: List<TV>) -> Unit, onError: () -> Unit) {
        api.getTVPopular(page = page)
            .enqueue(object : Callback<GetTVResponse> {
                override fun onResponse(call: Call<GetTVResponse>, response: Response<GetTVResponse>) {
                    getResponse(response, onSuccess, onError)
                }

                override fun onFailure(call: Call<GetTVResponse>, t: Throwable) {
                    onError.invoke()

                }
            })
    }

    override fun getTVDetailCredits(tv_id : String, page: Int, onSuccess: (castAndCrew : CastAndCrew)
    -> Unit, onError: () -> Unit) {
        api.getTVDetailCredits(tv_id = tv_id, page = page)
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

    private fun getResponse(response : Response<GetTVResponse>, onSuccess: (tv: List<TV>) -> Unit, onError: () -> Unit)
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