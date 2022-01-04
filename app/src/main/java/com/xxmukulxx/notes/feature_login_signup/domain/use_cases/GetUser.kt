package com.xxmukulxx.notes.feature_login_signup.domain.use_cases

import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup.domain.repository.UserDataRepository

class GetUser(private val repository: UserDataRepository) {
    operator fun invoke(): UserData? {
        return repository.getUser()
    }
}