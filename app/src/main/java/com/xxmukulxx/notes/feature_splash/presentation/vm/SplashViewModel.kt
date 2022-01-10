package com.xxmukulxx.notes.feature_splash.presentation.vm

import android.animation.Animator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.FragSplashBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.util.navigateFromSplash
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : BaseViewModel() {

    fun initAnimator(b: FragSplashBinding) {
        b.anim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}

            override fun onAnimationEnd(p0: Animator?) {
                proceed(b)
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })
    }

    fun proceed(b: FragSplashBinding) {
        val extras = FragmentNavigatorExtras(
            b.anim to "shared_card_loader"
        )
        val user = userUseCases.getUser()
        user?.run {
            b.root.navigateFromSplash(R.id.action_splashFragment_to_mainFragment, null, extras)
        } ?: run {
            b.root.navigateFromSplash(R.id.action_splashFragment_to_loginFragment, null, extras)
        }
    }
}