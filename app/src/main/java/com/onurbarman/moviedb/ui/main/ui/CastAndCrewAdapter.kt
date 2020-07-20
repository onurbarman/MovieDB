package com.onurbarman.moviedb.ui.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.moviedb.R
import com.onurbarman.moviedb.util.GlideUtils
import com.onurbarman.moviedb.data.model.CastAndCrew



class CastAndCrewAdapter(context : Context, private var cast: List<CastAndCrew.Cast>, private var crew: List<CastAndCrew.Crew>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val _context : Context = context
    val VIEW_TYPE_CAST = 0
    val VIEW_TYPE_CREW = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater .from(parent.context)
            .inflate(R.layout.cardview_fullcastandcrew, parent, false)

        if(viewType == VIEW_TYPE_CAST){
            return CastViewHolder(view)
        }

        if(viewType == VIEW_TYPE_CREW){
            return CrewViewHolder(view)
        }

        return CastViewHolder(view);
    }
    override fun getItemCount(): Int = cast.size+crew.size

    override fun getItemViewType(position: Int): Int {

        if(position < cast.size){
            return VIEW_TYPE_CAST
        }

        if(position - cast.size < crew.size){
            return VIEW_TYPE_CREW
        }

        return -1;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CastViewHolder){
            (holder as CastViewHolder).bind(cast.get(position))
        }

        if(holder is CrewViewHolder){
            (holder as CrewViewHolder).bind(crew.get(position - cast.size))
        }
    }

    fun updateCastAndCrew(cast: List<CastAndCrew.Cast>, crew: List<CastAndCrew.Crew>) {
        this.cast = cast
        this.crew=crew
        notifyDataSetChanged()
    }

    inner class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imagePerson)
        private val personName : TextView = itemView.findViewById(R.id.titlePersonName)
        fun bind(cast: CastAndCrew.Cast) {
            personName.text=cast.name
            GlideUtils.urlToImageView("https://image.tmdb.org/t/p/w154${cast.profile_path}",image,_context)
        }
    }

    inner class CrewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imagePerson)
        private val personName : TextView = itemView.findViewById(R.id.titlePersonName)
        fun bind(crew: CastAndCrew.Crew) {
            personName.text=crew.name
            GlideUtils.urlToImageView("https://image.tmdb.org/t/p/w154${crew.profile_path}",image,_context)
        }
    }
}