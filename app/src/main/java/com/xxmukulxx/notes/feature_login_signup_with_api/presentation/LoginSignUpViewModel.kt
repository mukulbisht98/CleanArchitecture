package com.xxmukulxx.notes.feature_login_signup_with_api.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_login_signup_with_api.presentation.fragments.LoginFragment
import com.xxmukulxx.notes.feature_login_signup_with_api.presentation.fragments.SignUpFragmentDirections
import com.xxmukulxx.notes.feature_networking.repository.NetworkRepository
import com.xxmukulxx.notes.feature_networking.util.ApiResponseWrapper
import com.xxmukulxx.notes.util.navigateWithAction
import com.xxmukulxx.notes.util.navigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val networkRepository: NetworkRepository
) : BaseViewModel() {


    private val email: MutableLiveData<String> =
        MutableLiveData("")
    private val password: MutableLiveData<String> =
        MutableLiveData("")
    private val name: MutableLiveData<String> =
        MutableLiveData("")


    fun onEmailChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            email.postValue(s.trim().toString())
    }

   private fun getEmail(): LiveData<String> {
        return email
    }

    fun onPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            password.postValue(s.toString())
    }

     private  fun getPassword(): LiveData<String> {
        return password
    }

    fun onNameChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            name.postValue(s.toString())
    }

    private fun getName(): LiveData<String> {
        return name
    }

    fun handleClick(view: View) {
        when (view.id) {
            R.id.bnLogin -> {
                isLoading.postValue(true)
                performLogin()

            }
            R.id.bnSignup -> {
                isLoading.postValue(true)
                performSignUp(view)

            }
        }
    }

    private fun performLogin() {
        viewModelScope.launch {
            when (val response = networkRepository.callLoginApi(
                getEmail().value.toString(),
                getPassword().value.toString()
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

    private fun performSignUp(view: View) {
        viewModelScope.launch {
            when (val response = networkRepository.callSingUpApi(
                getName().value.toString(),
                getEmail().value.toString(),
                getPassword().value.toString()
            )) {
                is ApiResponseWrapper.Success -> {
                    isLoading.postValue(false)
                    Log.e("API RES --->", response.value.toString())
                    showToast("SignUp Successfully. Please Login Now.")
                    val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(getEmail().value.toString(), getPassword().value.toString())
                    view.navigateWithAction(action)
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