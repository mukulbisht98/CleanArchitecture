package com.xxmukulxx.notes.feature_login_signup.domain.repository

import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData

interface UserDataRepository {
    fun getUser(): UserData?

    suspend fun insertUser(user: UserData)

    suspend fun deleteUser()
}