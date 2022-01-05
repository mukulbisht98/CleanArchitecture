package com.xxmukulxx.notes.feature_profile.presentation.vm

import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.ProfileFragBinding
import com.xxmukulxx.notes.feature_login_signup.domain.model.UserData
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.capitalize
import com.xxmukulxx.notes.util.getString
import com.xxmukulxx.notes.util.setImgProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(userUseCases: UserUseCases) :
    BaseViewModel() {

    lateinit var b: ProfileFragBinding
    lateinit var mainFragment: MainFragment

    fun setAppBar() {
        mainFragment.setAppBar(getString(R.string.profile))
    }

    fun setProfilePic() {
        b.ivProfilePic.setImgProfile("https://picsum.photos/200/200")
    }

    private val profileData: UserData? = userUseCases.getUser()
    val email = profileData?.user?.email
    val name = profileData?.user?.name?.capitalize()
    val greetings = "Hello, $name"
}