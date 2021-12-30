package com.xxmukulxx.notes.feature_login.data.data_source

import androidx.room.*
import com.xxmukulxx.notes.feature_login.domain.model.UserData
import com.xxmukulxx.notes.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM userdata")
    fun getUser(): UserData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserData)

    @Query("DELETE from userdata")
    suspend fun deleteUser()
}
