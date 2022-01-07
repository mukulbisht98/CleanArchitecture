package com.xxmukulxx.notes.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImgFromURL")
fun loadImage(view: ImageView, imageUrl: String?) {
    view.setImg(imageUrl)
}