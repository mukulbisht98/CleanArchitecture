package com.xxmukulxx.notes.feature_login_signup.data.repository

import com.xxmukulxx.notes.feature_login_signup.data.data_source.UserDao
import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup.domain.repository.UserDataRepository

class UserDataRepositoryImpl(private val userDao: UserDao) : UserDataRepository {
    override fun getUser(): UserData? {
        return userDao.getUser()
    }

    override suspend fun insertUser(user: UserData) {
        userDao.insertUser(user)
    }

    override suspend fun deleteUser() {
        userDao.deleteUser()
    }
}