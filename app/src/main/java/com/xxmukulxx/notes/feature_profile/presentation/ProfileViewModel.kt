package com.xxmukulxx.notes.feature_profile.presentation

import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(userUseCases: UserUseCases) :
    BaseViewModel() {

    private val profileData: UserData? = userUseCases.getUser()

    val email = profileData?.user?.email
    val name = profileData?.user?.name
}