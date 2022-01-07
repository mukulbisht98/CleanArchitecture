package com.xxmukulxx.notes.feature_home.presentation

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.HomeFragBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(override val layoutResId: Int = R.layout.frag_home) : BaseFragment() {

    private lateinit var binding: HomeFragBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as HomeFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun viewModelInit() {
        viewModel.mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
        viewModel.setAppBar()
    }
}