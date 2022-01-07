package com.xxmukulxx.notes.feature_profile.presentation.fragment

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.FragProfileBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_profile.presentation.vm.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment(override val layoutResId: Int = R.layout.frag_profile) : BaseFragment() {

    private lateinit var binding: FragProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as FragProfileBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun viewModelInit() {
        viewModel.apply {
            b = binding
            mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
            setAppBar()
            setProfilePic()
            setRecyclerViews()
        }
    }
}