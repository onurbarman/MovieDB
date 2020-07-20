package com.onurbarman.moviedb.ui.main.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.moviedb.data.model.Movies
import com.onurbarman.moviedb.R


class MoviesFragment : Fragment(){

    private lateinit var nowPlayingMovies: RecyclerView
    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter

    private lateinit var popularMovies: RecyclerView
    private lateinit var popularMoviesAdapter: PopularMoviesAdapter

    val viewModel : MoviesViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingMovies = view.findViewById(R.id.recyclerViewNowPlaying)
        popularMovies = view.findViewById(R.id.recyclerViewPopular)

        getMoviesList(viewModel)
    }

    fun getMoviesList(viewModel: MoviesViewModel)
    {

        nowPlayingMovies.layoutManager = LinearLayoutManager( context, LinearLayoutManager.HORIZONTAL, false )
        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter(requireContext(), listOf()) { movies -> showMoviesDetails(movies) }

        viewModel.getNowPlayingMoviesObservable()!!.observe(viewLifecycleOwner,
            Observer<List<Movies>> { movies ->
                if (movies != null) {
                    nowPlayingMoviesAdapter.updateNowPlayingMovies(movies)
                    nowPlayingMovies.adapter = nowPlayingMoviesAdapter
                }
            })

        popularMovies.layoutManager = GridLayoutManager(context,2)
        popularMoviesAdapter = PopularMoviesAdapter(requireContext(), listOf()) { movies -> showMoviesDetails(movies) }

        viewModel.getPopularMoviesObservable()!!.observe(viewLifecycleOwner,
            Observer<List<Movies>> { movies ->
                if (movies != null) {
                    popularMoviesAdapter.updatePopularMovies(movies)
                    popularMovies.adapter = popularMoviesAdapter
                }
            })

    }

    private fun showMoviesDetails(movie: Movies) {
        val args: Bundle = Bundle()

        addDetailsToBundle(args,movie)

        val moviesDetailFragment : MoviesDetailFragment =
            MoviesDetailFragment()
        moviesDetailFragment.arguments=args

        getParentFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, moviesDetailFragment,"MoviesDetailFragment")
            .addToBackStack("MoviesFragment")
            .commit()
    }

    private fun addDetailsToBundle(args : Bundle,movie : Movies)
    {
        var moviesDetailArrayList : ArrayList<String> = ArrayList<String>()
        moviesDetailArrayList.add(movie.title)
        moviesDetailArrayList.add(movie.backdropPath)
        moviesDetailArrayList.add(movie.posterPath)
        moviesDetailArrayList.add(movie.overview)

        args.putDouble("MoviesRatingScore",movie.rating)
        args.putInt("MoviesDetailVoteCount",movie.vote_count)
        args.putString("MoviesDetailId",movie.id.toString())
        args.putStringArrayList("MoviesDetailArrayList",moviesDetailArrayList)
    }

    
}