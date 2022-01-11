package com.xxmukulxx.notes.common.feature_app_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmukulxx.notes.feature_product.data.data_cource.ProductDao
import com.xxmukulxx.notes.feature_product.domain.model.ProductData


/**
 * API cache database
 */
const val SCHEMA_VERSION = 1


@Database(
    entities = [
        ProductData::class,
    ],
    version = SCHEMA_VERSION
)

abstract class AppDb : RoomDatabase() {
    abstract fun productCache(): ProductDao
}
