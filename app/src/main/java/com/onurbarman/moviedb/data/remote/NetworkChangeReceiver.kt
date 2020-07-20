package com.onurbarman.moviedb.data.remote

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

open class NetworkChangeReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent!!.action))
        {
            val noConnectivity : Boolean = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            if(noConnectivity)
                Toast.makeText(context,"Internet Bağlantısı Yok", Toast.LENGTH_SHORT).show()
        }

    }
}