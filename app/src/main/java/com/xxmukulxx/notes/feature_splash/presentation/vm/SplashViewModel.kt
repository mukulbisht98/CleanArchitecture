package com.xxmukulxx.notes.feature_splash.presentation.vm

import android.animation.Animator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.SplashLayoutBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.util.navigateWithId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val userUseCases: UserUseCases) :
    BaseViewModel() {

    fun initAnimator(b: SplashLayoutBinding) {
        b.anim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                proceed(b)
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })
    }

    fun proceed(b: SplashLayoutBinding) {
        val extras = FragmentNavigatorExtras(
            b.anim to "shared_card_loader"
        )
        val user = userUseCases.getUser()
        user?.run {

            b.root.navigateWithId(R.id.action_splashFragment_to_mainFragment, null, extras)
        } ?: run {
            b.root.navigateWithId(R.id.action_splashFragment_to_loginFragment, null, extras)
        }
    }

}