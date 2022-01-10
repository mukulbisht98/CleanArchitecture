package com.xxmukulxx.notes.feature_product.presentation.fragment

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.FragAddProductBinding
import com.xxmukulxx.notes.feature_product.presentation.vm.ProductsViewModel

class AddProduct(override val layoutResId: Int = R.layout.frag_add_product) : BaseFragment() {

    private lateinit var binding: FragAddProductBinding
    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.apply {
            b = binding
            setAppBar()
        }
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as FragAddProductBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}