package com.onurbarman.moviedb.ui.main.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurbarman.moviedb.data.model.Movies
import com.onurbarman.moviedb.data.repository.movies.MoviesRepository

class MoviesViewModel() : ViewModel() {

    private var nowPlayingMoviesList: MutableLiveData<List<Movies>>? = null
    private var popularMoviesList: MutableLiveData<List<Movies>>? = null

    init {
        nowPlayingMoviesList = MutableLiveData()
        MoviesRepository.getNowPlayingMovies(onSuccess = {it -> nowPlayingMoviesList?.setValue(it)},onError = {Log.d("error","onError")})

        popularMoviesList = MutableLiveData()
        MoviesRepository.getPopularMovies(onSuccess = {it -> popularMoviesList?.setValue(it)},onError = {Log.d("error","onError")})
    }

    fun getNowPlayingMoviesObservable(): LiveData<List<Movies>>? {
        return nowPlayingMoviesList
    }

    fun getPopularMoviesObservable(): LiveData<List<Movies>>? {
        return popularMoviesList
    }

}