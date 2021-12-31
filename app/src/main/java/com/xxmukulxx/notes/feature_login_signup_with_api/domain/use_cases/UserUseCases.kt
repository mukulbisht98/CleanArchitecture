package com.xxmukulxx.notes.feature_login_signup_with_api.domain.use_cases

data class UserUseCases(
    val getUser: GetUser,
    val insertUser: InsertUser,
    val deleteUser: DeleteUser,
)