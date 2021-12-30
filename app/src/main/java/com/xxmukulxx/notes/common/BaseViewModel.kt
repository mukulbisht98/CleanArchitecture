package com.xxmukulxx.notes.common

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xxmukulxx.notes.MyApplication
import com.xxmukulxx.notes.R

abstract class BaseViewModel(val application: MyApplication) : ViewModel() {
    protected val isLoading = MutableLiveData<Boolean>()
    protected val displayError = MutableLiveData<String>()

    fun getIsLoadingLiveData(): LiveData<Boolean> {
        return isLoading
    }

    fun getDisplayErrorLiveData(): LiveData<String> {
        return displayError
    }

    fun showToast(message: String) {
        Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
    }

    protected fun handleNetworkError() {
        isLoading.postValue(false)
        displayError.postValue(application.resources.getString(R.string.network_error))
    }

    protected fun handleUnauthorizedError() {
        isLoading.postValue(false)
        //Pending
    }
}