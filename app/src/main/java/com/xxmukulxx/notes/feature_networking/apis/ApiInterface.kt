package com.xxmukulxx.notes.feature_networking.apis

import com.xxmukulxx.notes.feature_login.domain.model.UserData
import com.xxmukulxx.notes.feature_login.domain.model.UserReq
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST(Apis.LOGIN_API)
    suspend fun login(
        @Body req: UserReq
    ): UserData
}