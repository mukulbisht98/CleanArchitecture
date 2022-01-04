package com.xxmukulxx.notes.feature_splash.presentation

import android.animation.Animator
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.SplashLayoutBinding
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.util.navigateWithId
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private lateinit var b: SplashLayoutBinding
    @Inject
    lateinit var userUseCases: UserUseCases

    override val layoutResId: Int
        get() = R.layout.splash_layout

    override fun onCreateView() {
        initBindingsAndViewModel()
        initAnimation()
    }

    private fun initBindingsAndViewModel() {
        b = getBinding() as SplashLayoutBinding
    }

    private fun initAnimation() {
        b.anim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {}
            override fun onAnimationEnd(p0: Animator?) {
                val user = userUseCases.getUser()
                user?.run {
                    b.root.navigateWithId(R.id.action_splashFragment_to_mainFragment)
                } ?: run {
                    b.root.navigateWithId(R.id.action_splashFragment_to_loginFragment)
                }
            }
            override fun onAnimationCancel(p0: Animator?) {}
            override fun onAnimationRepeat(p0: Animator?) {}
        })
    }
}