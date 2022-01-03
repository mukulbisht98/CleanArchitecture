package com.xxmukulxx.notes.feature_home.presentation

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.HomeFragBinding

class HomeFragment(override val layoutResId: Int = R.layout.home_frag) : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragBinding
    override fun onCreateView() {
        initBindingsAndViewModel()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as HomeFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}