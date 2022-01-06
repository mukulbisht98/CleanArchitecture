package com.xxmukulxx.notes.feature_splash.presentation.fragments

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.SplashLayoutBinding
import com.xxmukulxx.notes.feature_splash.presentation.vm.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class SplashFragment(override val layoutResId: Int = R.layout.splash_layout) : BaseFragment() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var b: SplashLayoutBinding


    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModel.initAnimator(b)
    }

    private fun initBindingsAndViewModel() {
        b = getBinding() as SplashLayoutBinding
    }
}