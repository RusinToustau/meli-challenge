package com.meli.challenge.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadurl(url: String?){
    val glideUrl = GlideUrl(
        url, LazyHeaders.Builder()
            .addHeader(
                "Postman-Token",
                "749a49e8-bea8-2d4d-21c9-12b5982e1465"
            )
            .build()
    )
    Glide.with(context).load(glideUrl).apply(RequestOptions()).into(this)
}