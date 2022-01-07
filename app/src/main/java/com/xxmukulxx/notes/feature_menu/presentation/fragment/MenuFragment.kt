package com.xxmukulxx.notes.feature_menu.presentation.fragment

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.FragMenuBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_menu.presentation.vm.MenuViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment(override val layoutResId: Int = R.layout.frag_menu) : BaseFragment() {

    private lateinit var binding: FragMenuBinding
    private val viewModel: MenuViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as FragMenuBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun viewModelInit() {
        viewModel.apply {
            b = binding
            mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
            setAppBar()
            setupToggleListener()
        }
    }
}