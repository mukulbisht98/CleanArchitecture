package com.xxmukulxx.notes.feature_login.domain.repository

import com.xxmukulxx.notes.feature_login.domain.model.UserData

interface UserDataRepository {
    fun getUser(): UserData?

    suspend fun insertUser(user: UserData)

    suspend fun deleteUser()
}