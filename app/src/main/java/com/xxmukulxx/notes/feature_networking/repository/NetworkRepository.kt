package com.xxmukulxx.notes.feature_networking.repository

import com.xxmukulxx.notes.feature_login_signup_with_api.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.model.LoginReq
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.model.SignUpReq
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.model.SignUpResponse
import com.xxmukulxx.notes.feature_networking.apis.ApiInterface
import com.xxmukulxx.notes.feature_networking.util.ApiResponseWrapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val apiInterface: ApiInterface) :
    BaseRepository() {

    suspend fun callLoginApi(
        email: String,
        password: String
    ): ApiResponseWrapper<UserData> {
        return safeApiCall(Dispatchers.IO) {
            apiInterface.login(LoginReq(email, password))
        }
    }

    suspend fun callSingUpApi(
        name:String,
        email: String,
        password: String
    ): ApiResponseWrapper<SignUpResponse> {
        return safeApiCall(Dispatchers.IO) {
            apiInterface.signup(SignUpReq(name,email, password))
        }
    }
}