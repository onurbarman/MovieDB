package com.onurbarman.moviedb.ui.main.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.moviedb.R
import com.onurbarman.moviedb.util.GlideUtils
import com.onurbarman.moviedb.data.model.Movies

class NowPlayingMoviesAdapter(context: Context, private var movies: List<Movies>, private val onMovieClick: (movieList: Movies) -> Unit )
    : RecyclerView.Adapter<NowPlayingMoviesAdapter.MovieViewHolder>() {

    val _context : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.cardview_nowplaying_and_latest, parent, false)
        return MovieViewHolder(view)
    }
    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateNowPlayingMovies(movies: List<Movies>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.imageMovieTV)
        private val movieName : TextView = itemView.findViewById(R.id.titleMovieTV)
        fun bind(movie: Movies) {
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
            movieName.text=movie.title
            GlideUtils.urlToImageView("https://image.tmdb.org/t/p/w154${movie.posterPath}",poster,_context)
        }
    }
}
