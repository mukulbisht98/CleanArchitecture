package com.xxmukulxx.notes.feature_login.domain.use_cases

import com.xxmukulxx.notes.feature_login.domain.repository.UserDataRepository

class DeleteUser(
    private val repository: UserDataRepository
) {
    suspend operator fun invoke() {
        repository.deleteUser()
    }
}