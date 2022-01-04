package com.xxmukulxx.notes.feature_login_signup.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


class InvalidUserException(message: String) : Exception(message)

data class LoginReq(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
)

data class SignUpReq(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("email")
    var email: String = "",
    @SerializedName("password")
    var password: String = "",
)

@Entity
data class UserData(
    @PrimaryKey
    @SerializedName("token")
    var token: String,
    @SerializedName("user")
    @Embedded
    var user: User?,
    @SerializedName("error")
    var error: String?,
)

data class User(
    @SerializedName("email")
    var email: String = "",
    @SerializedName("_id")
    var id: String = "",
    @SerializedName("name")
    var name: String = ""
)

data class SignUpResponse(
    @SerializedName("message")
    var name: String = ""
)

