package com.xxmukulxx.notes.feature_login_signup.domain.use_cases

data class UserUseCases(
    val getUser: GetUser,
    val insertUser: InsertUser,
    val deleteUser: DeleteUser,
)