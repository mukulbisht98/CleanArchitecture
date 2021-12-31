package com.xxmukulxx.notes.feature_login_signup_with_api.domain.use_cases

import com.xxmukulxx.notes.feature_login_signup_with_api.domain.repository.UserDataRepository

class DeleteUser(
    private val repository: UserDataRepository
) {
    suspend operator fun invoke() {
        repository.deleteUser()
    }
}