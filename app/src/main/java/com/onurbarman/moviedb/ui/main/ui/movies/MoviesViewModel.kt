package com.onurbarman.moviedb.ui.main.ui.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurbarman.moviedb.data.model.Movies
import com.onurbarman.moviedb.data.repository.movies.MoviesRepository
import com.onurbarman.moviedb.data.repository.movies.MoviesRepositoryImpl

class MoviesViewModel() : ViewModel() {

    private var moviesRepository : MoviesRepository = MoviesRepositoryImpl()
    private var nowPlayingMoviesList: MutableLiveData<List<Movies>>? = null
    private var popularMoviesList: MutableLiveData<List<Movies>>? = null

    init {
        nowPlayingMoviesList = MutableLiveData()
        moviesRepository.getNowPlayingMovies(onSuccess = {it -> nowPlayingMoviesList?.setValue(it)},onError = {Log.d("error","onError")})

        popularMoviesList = MutableLiveData()
        moviesRepository.getPopularMovies(onSuccess = {it -> popularMoviesList?.setValue(it)},onError = {Log.d("error","onError")})
    }

    fun getNowPlayingMoviesObservable(): LiveData<List<Movies>>? {
        return nowPlayingMoviesList
    }

    fun getPopularMoviesObservable(): LiveData<List<Movies>>? {
        return popularMoviesList
    }

}