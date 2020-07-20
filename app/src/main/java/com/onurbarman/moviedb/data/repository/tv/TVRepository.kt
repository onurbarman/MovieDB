package com.onurbarman.moviedb.data.repository.tv

import com.onurbarman.moviedb.data.model.TV
import com.onurbarman.moviedb.data.model.CastAndCrew

interface TVRepository {

    fun getTVTopRated(page: Int = 1, onSuccess: (tv: List<TV>) -> Unit, onError: () -> Unit)

    fun getTVPopular(page: Int = 1, onSuccess: (tv: List<TV>) -> Unit, onError: () -> Unit)

    fun getTVDetailCredits(tv_id : String, page: Int = 1, onSuccess: (castAndCrew : CastAndCrew) -> Unit, onError: () -> Unit)

}