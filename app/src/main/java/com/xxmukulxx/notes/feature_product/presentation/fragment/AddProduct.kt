package com.xxmukulxx.notes.feature_product.presentation.fragment

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.FragAddProductBinding
import com.xxmukulxx.notes.feature_product.presentation.vm.ProductsViewModel
import com.xxmukulxx.notes.util.setImgFromUri
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProduct(override val layoutResId: Int = R.layout.frag_add_product) : BaseFragment() {

    private lateinit var binding: FragAddProductBinding
    private val viewModel: ProductsViewModel by viewModels()

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                data?.data?.let {
                    binding.ivAddProductImage.setImgFromUri(it)
                    viewModel.tempProductImageUri = it
                }
            }
        }

    override fun onCreateView() {
        initBindingsAndViewModel()
        initViewModel()
        observer()
    }

    private fun initViewModel() {
        viewModel.apply {
            b = binding
            setupItems()
            setAppBar(resultLauncher)
        }
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as FragAddProductBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun observer() {
        viewModel.getIsLoadingLiveData().observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

}