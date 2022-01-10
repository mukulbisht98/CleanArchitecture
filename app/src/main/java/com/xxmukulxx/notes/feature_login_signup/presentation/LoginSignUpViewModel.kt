package com.xxmukulxx.notes.feature_login_signup.presentation

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.notes.MyApplication
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.FragLoginBinding
import com.xxmukulxx.notes.databinding.FragSignupBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_login_signup.presentation.fragments.SignUpFragmentDirections
import com.xxmukulxx.notes.feature_networking.repository.NetworkRepository
import com.xxmukulxx.notes.feature_networking.util.ApiResponseWrapper
import com.xxmukulxx.notes.util.*
import com.xxmukulxx.notes.util.validation.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginSignUpViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val networkRepository: NetworkRepository
) : BaseViewModel() {


    lateinit var bLogin: FragLoginBinding
    lateinit var bSignUp: FragSignupBinding

    private val email: MutableLiveData<String> =
        MutableLiveData("")
    private val password: MutableLiveData<String> =
        MutableLiveData("")
    private val name: MutableLiveData<String> =
        MutableLiveData("")

    fun initAppBarSignUp() {
        bSignUp.layoutAppBar.tvTitle.text = getString(R.string.signup)
        bSignUp.layoutAppBar.ivBack.setOnClickListener { handleClick(it) }
        bSignUp.layoutAppBar.ivInfo.hide()
    }

    fun initAppBarLogin() {
        bLogin.layoutAppBar.tvTitle.text = getString(R.string.login)
        bLogin.layoutAppBar.ivBack.hide()
        bLogin.layoutAppBar.ivInfo.hide()
    }

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
                if (checkValidation(MyApplication.appContext)) {
                    isLoading.postValue(true)
                    performLogin(view)
                }

            }
            R.id.bnSignup -> {
                if (checkValidation(MyApplication.appContext, true)) {
                    isLoading.postValue(true)
                    performSignUp(view)
                }
            }
            R.id.ivBack -> {
                view.navigateBack()
            }
            R.id.ivInfo -> {

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
                    toast("Logged in Successfully...")
                    userUseCases.insertUser(response.value)
                    view.navigateWithViewModel(R.id.action_loginFragment_to_mainFragment)
                }
                is ApiResponseWrapper.GenericError -> {
                    isLoading.postValue(false)
                    toast("API GenericError ---> ${response.errorMessage}")
                    Log.e("API GenericError --->", response.errorMessage.toString())
                }
                is ApiResponseWrapper.UnauthorizedError -> {
                    isLoading.postValue(false)
                    toast("API UnauthorizedError ---> $response")
                    Log.e("API UnauthorizedError --->", response.toString())
                }
                is ApiResponseWrapper.NetworkError -> {
                    isLoading.postValue(false)
                    toast("API NetworkError ---> $response")
                    Log.e("API NetworkError --->", response.toString())
                }
                is ApiResponseWrapper.UserInvalid -> {
                    isLoading.postValue(false)
                    toast("API UserInvalid ---> $response")
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
                    toast("SignUp Successfully. Please Login Now.")
                    val action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment(
                        getEmail().value.toString(),
                        getPassword().value.toString()
                    )
                    view.navigateWithAction(action)
                }
                is ApiResponseWrapper.GenericError -> {
                    isLoading.postValue(false)
                    toast("API GenericError ---> ${response.errorMessage}")
                    Log.e("API GenericError --->", response.errorMessage.toString())
                }
                is ApiResponseWrapper.UnauthorizedError -> {
                    isLoading.postValue(false)
                    toast("API UnauthorizedError ---> $response")
                    Log.e("API UnauthorizedError --->", response.toString())
                }
                is ApiResponseWrapper.NetworkError -> {
                    isLoading.postValue(false)
                    toast("API NetworkError ---> $response")
                    Log.e("API NetworkError --->", response.toString())
                }
                is ApiResponseWrapper.UserInvalid -> {
                    isLoading.postValue(false)
                    toast("API UserInvalid ---> $response")
                    Log.e("API UserInvalid --->", response.toString())
                }
            }
        }
    }

}