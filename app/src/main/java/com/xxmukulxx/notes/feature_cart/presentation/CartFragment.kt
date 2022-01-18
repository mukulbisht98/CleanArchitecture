package com.xxmukulxx.notes.feature_cart.presentation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.notes.databinding.FragCartBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_main.presentation.MainFragmentDirections
import com.xxmukulxx.notes.util.hide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment(override val layoutResId: Int = R.layout.frag_cart) : BaseFragment() {

    private lateinit var binding: FragCartBinding

    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as FragCartBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun viewModelInit() {
        viewModel.apply {
            mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
            productListLiveData.observe(requireActivity(), { list ->
                if (list.isNotEmpty()) binding.tvNoItemsInCart.hide()
                binding.rvCartItemList.adapter =
                    RecyclerAdapter(list.toMutableList(), R.layout.item_product_list) {
                        val action = MainFragmentDirections.actionMainFragmentToProductDetails(
                            list[it].id.toString()
                        )
                        mainFragment.findNavController()
                            .navigate(action)
                    }
            })
            binding.rvCartItemList.layoutManager = LinearLayoutManager(context)
            setAppBar()
        }
    }
}