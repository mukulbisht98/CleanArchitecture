package com.xxmukulxx.notes.feature_note.presentation

import androidx.lifecycle.ViewModel
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val userUseCases: UserUseCases) : ViewModel() {
    fun checkUser() {
        userUseCases.getUser()?.let {

        }?: run {

        }
    }
}