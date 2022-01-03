package com.xxmukulxx.notes.feature_profile.presentation

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.ProfileFragBinding

class ProfileFragment(override val layoutResId: Int = R.layout.profile_frag) : BaseFragment() {
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: ProfileFragBinding
    override fun onCreateView() {
        initBindingsAndViewModel()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as ProfileFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}