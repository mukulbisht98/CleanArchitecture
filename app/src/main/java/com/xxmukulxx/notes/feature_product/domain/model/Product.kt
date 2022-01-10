package com.xxmukulxx.notes.feature_product.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val title: String,
    val type: String,
    val description: String,
    val price: Float,
    val imgUrl: String = "https://picsum.photos/250/200",
)
