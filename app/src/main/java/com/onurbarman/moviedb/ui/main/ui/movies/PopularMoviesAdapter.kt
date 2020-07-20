package com.onurbarman.moviedb.ui.main.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.moviedb.R
import com.onurbarman.moviedb.util.GlideUtils
import com.onurbarman.moviedb.data.model.Movies

class PopularMoviesAdapter(context: Context, private var popularMovies: List<Movies>, private val onMovieClick: (movieList: Movies) -> Unit )
    : RecyclerView.Adapter<PopularMoviesAdapter.PopularMovieViewHolder>() {

    val _context : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.cardview_popular_movies, parent, false)
        return PopularMovieViewHolder(view)
    }
    override fun getItemCount(): Int = popularMovies.size

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        holder.bind(popularMovies[position])
    }

    fun updatePopularMovies(movies: List<Movies>) {
        this.popularMovies = movies
        notifyDataSetChanged()
    }

    inner class PopularMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.imagePopularMovie)
        private val movieName : TextView = itemView.findViewById(R.id.textPopularMovieName)
        private val movieDate : TextView = itemView.findViewById(R.id.textPopularMovieDate)
        private val voteFirst : TextView = itemView.findViewById(R.id.textPopularMovieVoteFirst)
        private val voteSecond : TextView = itemView.findViewById(R.id.textPopularMovieVoteSecond)

        fun bind(movie: Movies) {
            itemView.setOnClickListener { onMovieClick.invoke(movie) }
            movieName.text=movie.title


            convertToShortDateFormat(movie.releaseDate,movieDate)
            getVoteStrings(movie.rating,voteFirst,voteSecond)

            GlideUtils.urlToImageView("https://image.tmdb.org/t/p/w154${movie.posterPath}",poster,_context)
        }
    }

    private fun convertToShortDateFormat(dateString : String,movieDate : TextView)
    {
        val shortDate : String = dateString.substring(0,4)
        movieDate.text=shortDate
    }

    private fun getVoteStrings(rating: Double, voteFirst: TextView, voteSecond: TextView) {
        val voteString : String = rating.toString()
        voteFirst.text=voteString.substring(0,1)
        voteSecond.text=voteString.substring(1,3)
    }
}