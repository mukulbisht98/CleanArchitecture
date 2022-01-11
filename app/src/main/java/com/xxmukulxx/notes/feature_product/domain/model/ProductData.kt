package com.xxmukulxx.notes.feature_product.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val type: String,
    val description: String,
    val quantity: Int = 0,
    val price: Float,
    var rating: Float = 0f,
    val imgUrl: String = "https://picsum.photos/250/200",
)
