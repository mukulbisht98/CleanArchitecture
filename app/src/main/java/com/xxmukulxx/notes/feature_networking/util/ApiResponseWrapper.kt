package com.xxmukulxx.notes.feature_networking.util

sealed class ApiResponseWrapper<out T> {
    data class Success<out T>(val value: T) : ApiResponseWrapper<T>()
    data class GenericError(val code: Int?, val errorMessage: String?) :
        ApiResponseWrapper<Nothing>()

    data class UserInvalid(val code: Int?, val errorMessage: String?) :
        ApiResponseWrapper<Nothing>()

    object UnauthorizedError : ApiResponseWrapper<Nothing>()
    object NetworkError : ApiResponseWrapper<Nothing>()
}