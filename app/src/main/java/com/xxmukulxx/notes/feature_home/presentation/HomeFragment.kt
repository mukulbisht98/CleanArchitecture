package com.xxmukulxx.notes.feature_home.presentation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.notes.databinding.FragHomeBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_main.presentation.MainFragmentDirections
import com.xxmukulxx.notes.util.hide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment(override val layoutResId: Int = R.layout.frag_home) : BaseFragment() {

    private lateinit var binding: FragHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as FragHomeBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun viewModelInit() {
        viewModel.apply {
            mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
            productListLiveData.observe(requireActivity(), { list ->
                if (list.isNotEmpty()) binding.tvNoProductsToDisplay.hide()
                binding.rvProductList.adapter =
                    RecyclerAdapter(list.toMutableList(), R.layout.item_product_list) {
                        val action = MainFragmentDirections.actionMainFragmentToProductDetails(
                            list[it].id.toString()
                        )
                        mainFragment.findNavController()
                            .navigate(action)
                    }
            })
            binding.rvProductList.layoutManager = LinearLayoutManager(context)
            setAppBar()
        }
    }
}