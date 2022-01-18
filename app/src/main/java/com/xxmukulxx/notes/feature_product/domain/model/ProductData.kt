package com.xxmukulxx.notes.feature_product.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val type: String,
    val description: String,
    val quantity: Int,
    val price: Float,
    var rating: Float,
    val imgUrl: String,
)
