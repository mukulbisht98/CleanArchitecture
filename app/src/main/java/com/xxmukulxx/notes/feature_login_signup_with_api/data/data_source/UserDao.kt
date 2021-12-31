package com.xxmukulxx.notes.feature_login_signup_with_api.data.data_source

import androidx.room.*
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.model.UserData

@Dao
interface UserDao {

    @Query("SELECT * FROM userdata")
    fun getUser(): UserData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserData)

    @Query("DELETE from userdata")
    suspend fun deleteUser()
}
