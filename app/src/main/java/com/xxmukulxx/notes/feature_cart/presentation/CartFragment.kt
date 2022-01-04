package com.xxmukulxx.notes.feature_cart.presentation

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.CartFragBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment(override val layoutResId: Int = R.layout.cart_frag) : BaseFragment() {

    private lateinit var binding: CartFragBinding

    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as CartFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun viewModelInit() {
        viewModel.mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
        viewModel.setAppBar()
    }
}