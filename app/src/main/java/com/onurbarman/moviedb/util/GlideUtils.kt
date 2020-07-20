package com.onurbarman.moviedb.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.DrawableCrossFadeTransition

class GlideUtils {
    companion object{

        fun urlToImageView(url:String, img: ImageView, context: Context)
        {
            Glide.with(context)
                .load(Uri.parse(url))
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCrop())
                .listener(MyImageRequestListener())
                .into(img)
        }
    }
}

class MyImageRequestListener()
    : RequestListener<Drawable> {

    override fun onResourceReady(
        resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

        target?.onResourceReady(
            resource!!,
            DrawableCrossFadeTransition(500, isFirstResource)
        )

        return true
    }

    override fun onLoadFailed(
        e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
        return false
    }




}
