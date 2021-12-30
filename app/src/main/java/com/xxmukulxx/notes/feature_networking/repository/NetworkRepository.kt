package com.xxmukulxx.notes.feature_networking.repository

import com.xxmukulxx.notes.feature_login.domain.model.UserData
import com.xxmukulxx.notes.feature_login.domain.model.UserReq
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
            apiInterface.login(UserReq(email, password))
        }
    }
}