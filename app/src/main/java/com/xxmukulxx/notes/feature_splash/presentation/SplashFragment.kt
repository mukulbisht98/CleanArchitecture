package com.xxmukulxx.notes.feature_splash.presentation

import android.animation.Animator
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.SplashLayoutBinding
import com.xxmukulxx.notes.feature_login.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.util.navigateWithId
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private lateinit var b: SplashLayoutBinding

    @Inject
    lateinit var userUseCases: UserUseCases
    var count = 0

    override fun getLayoutID(): Int {
        return R.layout.splash_layout
    }

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

            override fun onAnimationEnd(p0: Animator?) {}

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {
                if (++count > 0) {
                    count = 0
                    val user = userUseCases.getUser()
                    user?.run {
                        showToast("HOME! COMING SOON!")
                    } ?: run {
                        b.root.navigateWithId(R.id.action_splashFragment_to_loginFragment)
                    }
                }
            }
        })
    }
}