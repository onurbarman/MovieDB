package com.onurbarman.moviedb.ui.main.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.onurbarman.moviedb.R
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.moviedb.data.model.TV
import com.onurbarman.moviedb.data.repository.tv.TVRepository
import androidx.lifecycle.Observer

class TVFragment : Fragment(){

    private lateinit var topRatedTV: RecyclerView
    private lateinit var topRatedTVAdapter: TopRatedTVAdapter

    private lateinit var popularTVShows: RecyclerView
    private lateinit var popularTVShowsAdapter: PopularTvShowsAdapter

    val viewModel : TVViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedTV = view.findViewById(R.id.recyclerViewTopRated)
        popularTVShows = view.findViewById(R.id.recyclerViewPopularTVShows)

        getTVList(viewModel)
    }

    fun getTVList(viewModel : TVViewModel)
    {

        topRatedTV.layoutManager = LinearLayoutManager( context, LinearLayoutManager.HORIZONTAL, false )
        topRatedTVAdapter = TopRatedTVAdapter(requireContext(), listOf()) { tv -> showTVDetails(tv) }

        viewModel.getTopRatedTVObservable()!!.observe(viewLifecycleOwner,
            Observer<List<TV>> { tv ->
                if (tv != null) {
                    topRatedTVAdapter.updateTopRatedTV(tv)
                    topRatedTV.adapter = topRatedTVAdapter
                }
            })

        popularTVShows.layoutManager = GridLayoutManager(context,1)
        popularTVShowsAdapter = PopularTvShowsAdapter(requireContext(), listOf()) { tv -> showTVDetails(tv) }

        viewModel.getPopularTVShowsObservable()!!.observe(viewLifecycleOwner,
            Observer<List<TV>> { tv ->
                if (tv != null) {
                    popularTVShowsAdapter.updatePopularTVShows(tv)
                    popularTVShows.adapter = popularTVShowsAdapter
                }
            })

    }

    private fun showTVDetails(tv: TV) {
        val args: Bundle = Bundle()

        addDetailsToBundle(args,tv)

        val tvDetailFragment : TVDetailFragment =
            TVDetailFragment()
        tvDetailFragment.arguments=args

        getParentFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, tvDetailFragment,"TVDetailFragment")
            .addToBackStack("TVFragment")
            .commit()
    }

    private fun addDetailsToBundle(args : Bundle,tv : TV)
    {
        var tvDetailArrayList : ArrayList<String> = ArrayList<String>()
        tvDetailArrayList.add(tv.name)
        tvDetailArrayList.add(tv.backdrop_path)
        tvDetailArrayList.add(tv.poster_path)
        tvDetailArrayList.add(tv.overview)

        args.putDouble("TVRatingScore",tv.vote_average)
        args.putInt("TVDetailVoteCount",tv.vote_count)
        args.putString("TVDetailId",tv.id.toString())
        args.putStringArrayList("TVDetailArrayList",tvDetailArrayList)
    }


}