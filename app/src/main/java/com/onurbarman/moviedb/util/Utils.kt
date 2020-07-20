package com.onurbarman.moviedb.util

import android.content.Context
import android.content.Intent

class Utils {

    companion object{

        fun shareDetails(context: Context, detailTitle : String)
        {
            try {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plain"
                i.putExtra(Intent.EXTRA_SUBJECT, "MovieDB Uygulaması")
                val sAux = "$detailTitle detaylarını görüntülüyorum."
                i.putExtra(Intent.EXTRA_TEXT, sAux)
                context.startActivity(Intent.createChooser(i, "Paylaş!"))
            } catch (e: Exception) {

            }
        }

    }
}