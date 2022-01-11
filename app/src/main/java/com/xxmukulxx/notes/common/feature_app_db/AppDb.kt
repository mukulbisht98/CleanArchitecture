package com.xxmukulxx.notes.common.feature_app_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmukulxx.notes.feature_login_signup.data.data_source.UserDao
import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_product.data.data_cource.ProductDao
import com.xxmukulxx.notes.feature_product.domain.model.ProductData


/**
 * API cache database
 */
const val SCHEMA_VERSION = 2

@Database(
    entities = [
        UserData ::class,
        ProductData::class,
    ],
    version = SCHEMA_VERSION
)

abstract class AppDb : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}
