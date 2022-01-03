package com.xxmukulxx.notes.feature_cart.presentation

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.CartFragBinding

class CartFragment(override val layoutResId: Int = R.layout.cart_frag) : BaseFragment() {
    private val viewModel: CartViewModel by viewModels()
    private lateinit var binding: CartFragBinding
    override fun onCreateView() {
        initBindingsAndViewModel()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as CartFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}