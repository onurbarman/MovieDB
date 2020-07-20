package com.onurbarman.moviedb.ui.main.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.moviedb.R
import com.onurbarman.moviedb.util.GlideUtils
import com.onurbarman.moviedb.util.Utils
import com.onurbarman.moviedb.data.model.CastAndCrew
import com.onurbarman.moviedb.ui.main.ui.CastAndCrewAdapter
import com.onurbarman.moviedb.ui.main.ui.CastAndCrewViewModel

class TVDetailFragment : Fragment() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var overview: TextView
    private lateinit var info:TextView
    private lateinit var voteFirst: TextView
    private lateinit var voteSecond: TextView

    private lateinit var linearTVBackButton: LinearLayout
    private lateinit var tvDetailShareButton: ImageView

    private lateinit var tvDetailArrayList : ArrayList<String>
    private var ratingScore : Double = 0.0
    private var id : String = "1"

    private lateinit var castAndCrew: RecyclerView
    private lateinit var castAndCrewAdapter: CastAndCrewAdapter

    val viewModel : CastAndCrewViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backdrop = view.findViewById(R.id.imageTVDetailBig)
        poster = view.findViewById(R.id.imageTVDetail)
        title = view.findViewById(R.id.textTVDetailName)
        rating = view.findViewById(R.id.tvDetailRatingBar)
        overview = view.findViewById(R.id.textTVDetailDescription)
        info = view.findViewById(R.id.textTVDetailInfo)
        voteFirst = view.findViewById(R.id.tvDetailVoteFirst)
        voteSecond = view.findViewById(R.id.tvDetailVoteSecond)

        tvDetailShareButton = view.findViewById(R.id.tvDetailShareButton)
        linearTVBackButton = view.findViewById(R.id.linearTVBackButton)

        tvDetailShareButton.setOnClickListener{
            shareButtonClick()
        }

        linearTVBackButton.setOnClickListener {
            backButtonClick()
        }

        castAndCrew = view.findViewById(R.id.recyclerViewTVFullCastAndCrew)

        tvDetailArrayList = requireArguments().getStringArrayList("TVDetailArrayList")!!
        ratingScore = requireArguments().getDouble("TVRatingScore",1.0)
        id = requireArguments().getString("TVDetailId","1")

        populateDetails()
    }

    private fun shareButtonClick() {
        Utils.shareDetails(requireContext(),title.text.toString())
    }

    private fun backButtonClick() {
        getParentFragmentManager().popBackStackImmediate()
    }

    private fun populateDetails() {
        tvDetailArrayList.get(1).let { backdropPath ->
            GlideUtils.urlToImageView("https://image.tmdb.org/t/p/w342$backdropPath",backdrop,requireContext())
        }
        tvDetailArrayList.get(2).let { posterPath ->
            GlideUtils.urlToImageView("https://image.tmdb.org/t/p/w154$posterPath",poster,requireContext())
        }

        title.text = tvDetailArrayList.get(0)
        voteFirst.text=ratingScore.toString().substring(0,1)
        voteSecond.text=ratingScore.toString().substring(1,3)
        rating.rating = (ratingScore/2f).toFloat()
        overview.text = tvDetailArrayList.get(3)
        info.text=requireArguments().getInt("TVDetailVoteCount",1).toString() +" People voted"

        getCastAndCrew()
    }

    private fun getCastAndCrew()
    {
        castAndCrew.layoutManager = LinearLayoutManager( context, LinearLayoutManager.HORIZONTAL, false )


        viewModel.getTVCastAndCrewObservable(id)!!.observe(viewLifecycleOwner,
            Observer<CastAndCrew> { castAndCrewData ->
                if (castAndCrewData != null) {
                    castAndCrewAdapter = CastAndCrewAdapter(requireContext(), castAndCrewData.cast, castAndCrewData.crew)
                    castAndCrew.adapter = castAndCrewAdapter
                    castAndCrewAdapter.updateCastAndCrew(castAndCrewData.cast, castAndCrewData.crew)
                }
            })
    }

}