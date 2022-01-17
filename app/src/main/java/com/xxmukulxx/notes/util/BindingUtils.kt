package com.xxmukulxx.notes.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.xxmukulxx.notes.MyApplication.AppContext.appContext
import com.xxmukulxx.notes.R

@BindingAdapter("setImgFromURL")
fun loadImage(view: ImageView, imageUrl: String?) {
    val radius = appContext.resources.getDimension(R.dimen._12sdp)
    view.setImgWithRadius(imageUrl, radius.toInt())
}

@BindingAdapter("setRoundImgFromURL")
fun setRoundImgFromURL(view: ImageView, imageUrl: String?) {
    view.setImg(imageUrl)
}