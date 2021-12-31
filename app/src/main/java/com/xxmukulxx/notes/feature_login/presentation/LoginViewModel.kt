package com.xxmukulxx.notes.feature_login.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.notes.MyApplication
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_login.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_networking.repository.NetworkRepository
import com.xxmukulxx.notes.feature_networking.util.ApiResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    app: MyApplication,
    private val userUseCases: UserUseCases,
    private val networkRepository: NetworkRepository
) : BaseViewModel(app) {

    private var email: String = ""
    private var password: String = ""

    fun onEmailChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        email = s.toString()
    }

    fun onPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        password = s.toString()
    }

    fun handleClick(view: View) {
        when (view.id) {
            R.id.bnLogin -> {
                isLoading.postValue(true)
                performLogin()
            }
        }
    }

    private fun performLogin() {
        viewModelScope.launch {
            when (val response = networkRepository.callLoginApi(
                email,
                password
            )) {
                is ApiResponseWrapper.Success -> {
                    isLoading.postValue(false)
                    Log.e("API RES --->", response.value.toString())
                    showToast("API RES ---> ${response.value}")
                    userUseCases.insertUser(response.value)
                }
                is ApiResponseWrapper.GenericError -> {
                    isLoading.postValue(false)
                    showToast("API GenericError ---> ${response.errorMessage}")
                    Log.e("API GenericError --->", response.errorMessage.toString())
                }
                is ApiResponseWrapper.UnauthorizedError -> {
                    isLoading.postValue(false)
                    showToast("API UnauthorizedError ---> $response")
                    Log.e("API UnauthorizedError --->", response.toString())
                }
                is ApiResponseWrapper.NetworkError -> {
                    isLoading.postValue(false)
                    showToast("API NetworkError ---> $response")
                    Log.e("API NetworkError --->", response.toString())
                }
                is ApiResponseWrapper.UserInvalid -> {
                    isLoading.postValue(false)
                    showToast("API UserInvalid ---> $response")
                    Log.e("API UserInvalid --->", response.toString())
                }
            }
        }
    }

}