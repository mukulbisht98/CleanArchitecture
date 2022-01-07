package com.xxmukulxx.notes.feature_profile.domain.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.xxmukulxx.notes.util.setImg


data class ProfileContent(
    val title: String,
    val productsList: MutableList<Product>
)

data class Product(
    val title: String,
    val imgUrl: String = "https://picsum.photos/250/200",
)