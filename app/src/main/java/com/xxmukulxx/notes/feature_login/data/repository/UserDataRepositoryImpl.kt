package com.xxmukulxx.notes.feature_login.data.repository

import com.xxmukulxx.notes.feature_login.data.data_source.UserDao
import com.xxmukulxx.notes.feature_login.domain.model.UserData
import com.xxmukulxx.notes.feature_login.domain.repository.UserDataRepository

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