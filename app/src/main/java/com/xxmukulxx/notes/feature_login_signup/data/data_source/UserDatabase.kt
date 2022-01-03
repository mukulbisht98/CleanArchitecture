package com.xxmukulxx.notes.feature_login_signup.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData

@Database(
    entities = [UserData::class],
    version = 1, exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME: String = "user_db"
    }
}