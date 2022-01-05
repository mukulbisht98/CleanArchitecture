package com.xxmukulxx.notes.feature_profile.presentation.fragment

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.notes.databinding.ProfileFragBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_profile.presentation.vm.ProfileViewModel
import com.xxmukulxx.notes.util.toast
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
            setProfilePic()
        }
        binding.rvProfileOptions.apply {
            val list = mutableListOf(
                "Your Order",
                "Buy Again",
                "Your Account",
                "Your Wishlist"
            )
            adapter = RecyclerAdapter(
                list, R.layout.item_profile_options
            ) {
                toast(list[it])
            }
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}