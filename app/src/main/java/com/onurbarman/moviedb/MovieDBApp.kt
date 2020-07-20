package com.onurbarman.moviedb

import androidx.multidex.MultiDexApplication

class MovieDBApp : MultiDexApplication(){

    init {
        if(appInstance ==null)
        {
            appInstance =this
        }
    }

    companion object {
        private var appInstance: MovieDBApp? = null
        fun appInstance() : MovieDBApp? {
            return appInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        if(appInstance ==null)
        {
            appInstance =this
        }
    }

}