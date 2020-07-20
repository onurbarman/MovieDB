package com.onurbarman.moviedb.ui.main.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurbarman.moviedb.data.model.CastAndCrew
import com.onurbarman.moviedb.data.repository.movies.MoviesRepository
import com.onurbarman.moviedb.data.repository.tv.TVRepository

class CastAndCrewViewModel() : ViewModel() {

    private var moviesCastAndCrew : MutableLiveData<CastAndCrew>?=null

    private var tvCastAndCrew : MutableLiveData<CastAndCrew>?=null

    init {
        moviesCastAndCrew=MutableLiveData()

        tvCastAndCrew=MutableLiveData()
    }

    fun getMoviesCastAndCrewObservable(movie_id : String): LiveData<CastAndCrew>? {
        MoviesRepository.getMovieDetailCredits(movie_id,
            onSuccess = { it -> moviesCastAndCrew?.setValue(it)}
            ,onError = { Log.d("error","onError")})
        return moviesCastAndCrew
    }

    fun getTVCastAndCrewObservable(tv_id : String): LiveData<CastAndCrew>? {
        TVRepository.getTVDetailCredits(tv_id,
            onSuccess = { it -> tvCastAndCrew?.setValue(it)}
            ,onError = { Log.d("error","onError")})
        return tvCastAndCrew
    }

}