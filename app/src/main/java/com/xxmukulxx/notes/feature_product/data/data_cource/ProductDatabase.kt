package com.xxmukulxx.notes.feature_product.data.data_cource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmukulxx.notes.feature_product.domain.model.ProductData

@Database(
    entities = [ProductData::class],
    version = 1
)
abstract class ProductDatabase : RoomDatabase() {

    abstract val productDao: ProductDao

    companion object {
        const val DATABASE_NAME: String = "products_db"
    }
}