package com.xxmukulxx.notes.feature_profile.presentation

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.ProfileFragBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment(override val layoutResId: Int = R.layout.profile_frag) : BaseFragment() {

    private lateinit var binding: ProfileFragBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as ProfileFragBinding
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