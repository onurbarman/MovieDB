package com.onurbarman.moviedb.data.repository.movies

import com.onurbarman.moviedb.data.model.CastAndCrew
import com.onurbarman.moviedb.data.model.Movies

interface MoviesRepository {

    fun getNowPlayingMovies(page: Int = 1, onSuccess: (movies: List<Movies>) -> Unit, onError: () -> Unit)

    fun getPopularMovies(page: Int = 1, onSuccess: (movies: List<Movies>) -> Unit, onError: () -> Unit)

    fun getMovieDetailCredits(movie_id : String, page: Int = 1, onSuccess: (castAndCrew : CastAndCrew) -> Unit, onError: () -> Unit)
}
