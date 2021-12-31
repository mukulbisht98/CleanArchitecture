package com.xxmukulxx.notes.feature_login_signup_with_api.domain.use_cases

import com.xxmukulxx.notes.feature_login_signup_with_api.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.repository.UserDataRepository

class GetUser(private val repository: UserDataRepository) {
    operator fun invoke(): UserData? {
        return repository.getUser()
    }
}