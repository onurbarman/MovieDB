package com.onurbarman.moviedb.ui.main.ui.tv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.moviedb.R
import com.onurbarman.moviedb.util.GlideUtils
import com.onurbarman.moviedb.data.model.TV

class PopularTvShowsAdapter(context: Context, private var tv: List<TV>, private val onTVClick: (tvList: TV) -> Unit )
    : RecyclerView.Adapter<PopularTvShowsAdapter.TVViewHolder>() {

    val _context : Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.cardview_populartvshows, parent, false)
        return TVViewHolder(view)
    }
    override fun getItemCount(): Int = tv.size

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bind(tv[position])
    }

    fun updatePopularTVShows(tv: List<TV>) {
        this.tv = tv
        notifyDataSetChanged()
    }

    inner class TVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val poster: ImageView = itemView.findViewById(R.id.imagePopularShows)
        private val tvName : TextView = itemView.findViewById(R.id.textPopularTVName)
        private val voteFirst : TextView = itemView.findViewById(R.id.textPopularTVVoteFirst)
        private val voteSecond : TextView = itemView.findViewById(R.id.textPopularTVVoteSecond)

        fun bind(tv: TV) {
            itemView.setOnClickListener { onTVClick.invoke(tv) }

            tvName.text=tv.name
            getVoteStrings(tv.vote_average,voteFirst,voteSecond)

            GlideUtils.urlToImageView("https://image.tmdb.org/t/p/w342${tv.poster_path}",poster,_context)
        }
    }

    private fun getVoteStrings(rating: Double, voteFirst: TextView, voteSecond: TextView) {
        val voteString : String = rating.toString()
        //Log.d("deneme","gelen rating : ${voteString.substring()}")
        voteFirst.text=voteString.substring(0,1)
        voteSecond.text=voteString.substring(1,3)
    }
}