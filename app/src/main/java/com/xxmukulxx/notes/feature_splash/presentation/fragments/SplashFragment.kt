package com.xxmukulxx.notes.feature_splash.presentation.fragments

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.FragSplashBinding
import com.xxmukulxx.notes.feature_splash.presentation.vm.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment(override val layoutResId: Int = R.layout.frag_splash) : BaseFragment() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var b: FragSplashBinding

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModel.initAnimator(b)
    }

    private fun initBindingsAndViewModel() {
        b = getBinding() as FragSplashBinding
    }
}