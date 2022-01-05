package com.xxmukulxx.notes.feature_menu.presentation.fragment

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.MenuFragBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_menu.presentation.vm.MenuViewModel

class MenuFragment(override val layoutResId: Int = R.layout.menu_frag) : BaseFragment() {

    private lateinit var binding: MenuFragBinding
    private val viewModel: MenuViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as MenuFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun viewModelInit() {
        viewModel.mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
        viewModel.setAppBar()
    }
}