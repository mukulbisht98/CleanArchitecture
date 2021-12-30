package com.xxmukulxx.notes.feature_login.domain.use_cases

import com.xxmukulxx.notes.feature_login.domain.model.UserData
import com.xxmukulxx.notes.feature_login.domain.repository.UserDataRepository

class GetUser(private val repository: UserDataRepository) {
    operator fun invoke(): UserData? {
        return repository.getUser()
    }
}