package com.xxmukulxx.notes.feature_networking.apis

import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup.domain.model.LoginReq
import com.xxmukulxx.notes.feature_login_signup.domain.model.SignUpReq
import com.xxmukulxx.notes.feature_login_signup.domain.model.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST(Apis.LOGIN_API)
    suspend fun login(
        @Body req: LoginReq
    ): UserData

    @POST(Apis.SIGN_UP_API)
    suspend fun signup(
        @Body req: SignUpReq
    ): SignUpResponse
}