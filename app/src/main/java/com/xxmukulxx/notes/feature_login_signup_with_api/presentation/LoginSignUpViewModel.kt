package com.xxmukulxx.notes.feature_login_signup_with_api.presentation

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.template.validations.Validation
import com.xxmukulxx.notes.MyApplication
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_login_signup_with_api.presentation.fragments.SignUpFragmentDirections
import com.xxmukulxx.notes.feature_networking.repository.NetworkRepository
import com.xxmukulxx.notes.feature_networking.util.ApiResponseWrapper
import com.xxmukulxx.notes.util.navigateWithAction
import com.xxmukulxx.notes.util.navigateWithViewModel
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

    fun setEmail(email: String) {
        this.email.postValue(email)
    }

    fun onPasswordChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            password.postValue(s.toString())
    }

    private fun getPassword(): LiveData<String> {
        return password
    }

    fun setPass(pass: String) {
        password.postValue(pass)
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
                if (checkValidation(MyApplication.context)) {
                    isLoading.postValue(true)
                    performLogin(view)
                }

            }
            R.id.bnSignup -> {
                if (checkValidation(MyApplication.context, true)) {
                    isLoading.postValue(true)
                    performSignUp(view)
                }

            }
        }
    }

    private fun checkValidation(context: Context, isFromSignUpScreen: Boolean = false): Boolean {
        val validation = Validation().apply {
            if (isFromSignUpScreen) {
                isEmpty(name.value, context.getString(R.string.enter_your_name))
            }
            isEmpty(email.value, context.getString(R.string.enter_email_add))
            isEmailValid(email.value, context.getString(R.string.enter_valid_email))
            isEmpty(password.value, context.getString(R.string.enter_your_pass))
            /*~~~~~~~~~~future use~~~~~~~~~~~~~~~~~*/
            //isValidPassword(password.value, context.getString(R.string.enter_valid_pass))
        }

        return validation.isValid()
    }

    private fun performLogin(view: View) {
        viewModelScope.launch {
            when (val response = networkRepository.callLoginApi(
                getEmail().value.toString(),
                getPassword().value.toString()
            )) {
                is ApiResponseWrapper.Success -> {
                    isLoading.postValue(false)
                    Log.e("API RES --->", response.value.toString())
                    showToast("Logged in Successfully...")
                    userUseCases.insertUser(response.value)
                    view.navigateWithViewModel(R.id.action_loginFragment_to_mainFragment)
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
                    val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(
                        getEmail().value.toString(),
                        getPassword().value.toString()
                    )
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