package com.xxmukulxx.notes.feature_networking.repository

import android.util.Log
import com.xxmukulxx.notes.feature_networking.util.ApiResponseWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

const val GENERIC_ERROR_CODE = 1001
const val UNAUTHORIZED_ERROR_CODE = 401
const val NOT_PROCESSABLE_ENTITY = 422

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ApiResponseWrapper<T> {
        return withContext(dispatcher) {
            try {
                ApiResponseWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ApiResponseWrapper.NetworkError
                    is HttpException -> {
                        when (val code = throwable.code()) {
                            UNAUTHORIZED_ERROR_CODE -> {
                                ApiResponseWrapper.UnauthorizedError
                            }
                            NOT_PROCESSABLE_ENTITY -> {
                                Log.e("RES --> ", throwable.response()?.body().toString())
                                val errorResponse =
                                    "Email or Password is invalid."
                                ApiResponseWrapper.UserInvalid(code, errorResponse)
                            }
                            else -> {
                                val errorResponse =
                                    "Email or Password is invalid."
                                ApiResponseWrapper.GenericError(code, errorResponse)
                            }
                        }
                    }
                    else -> {
                        //handle according to use case
                        Log.e("API_ERROR ->", throwable.message ?: "")
                        ApiResponseWrapper.GenericError(GENERIC_ERROR_CODE, throwable.message)
                    }
                }
            }
        }
    }
}