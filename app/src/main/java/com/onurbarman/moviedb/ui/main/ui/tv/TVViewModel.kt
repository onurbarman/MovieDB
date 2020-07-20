package com.onurbarman.moviedb.ui.main.ui.tv

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onurbarman.moviedb.data.model.TV
import com.onurbarman.moviedb.data.repository.tv.TVRepository
import com.onurbarman.moviedb.data.repository.tv.TVRepositoryImpl

class TVViewModel() : ViewModel() {

    private var tvRepository : TVRepository = TVRepositoryImpl()
    private var topRatedTVList: MutableLiveData<List<TV>>? = null
    private var popularTVShowsList: MutableLiveData<List<TV>>? = null

    init {
        topRatedTVList = MutableLiveData()
        tvRepository.getTVTopRated(onSuccess = { it -> topRatedTVList?.setValue(it)},onError = { Log.d("error","onError")})

        popularTVShowsList = MutableLiveData()
        tvRepository.getTVPopular(onSuccess = { it -> popularTVShowsList?.setValue(it)},onError = { Log.d("error","onError")})
    }

    fun getTopRatedTVObservable(): LiveData<List<TV>>? {
        return topRatedTVList
    }

    fun getPopularTVShowsObservable(): LiveData<List<TV>>? {
        return popularTVShowsList
    }

}