package com.xxmukulxx.notes.feature_login_signup.domain.use_cases

import com.xxmukulxx.notes.feature_login_signup.domain.model.InvalidUserException
import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup.domain.repository.UserDataRepository

class InsertUser(private val repository: UserDataRepository) {

    @Throws(InvalidUserException::class)
    suspend operator fun invoke(res: UserData) {
//        val user = UserDataMapper.fromUserResToUserData(res)
        res.let {
            if (res.user?.email!!.isBlank()) {
                throw InvalidUserException("Email can't be empty.")
            }
            if (res.user?.name!!.isBlank()) {
                throw InvalidUserException("Username can't be empty.")
            }
            repository.insertUser(res)
        }
    }
}
