package com.xxmukulxx.notes.feature_menu.presentation.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.databinding.MenuFragBinding
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_menu.presentation.vm.MenuViewModel
import com.xxmukulxx.notes.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect

@InternalCoroutinesApi
@AndroidEntryPoint
class MenuFragment(override val layoutResId: Int = R.layout.menu_frag) : BaseFragment() {

    private lateinit var binding: MenuFragBinding
    private val viewModel: MenuViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()



    override fun onCreateView() {
        initBindingsAndViewModel()
        viewModelInit()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as MenuFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }



    @InternalCoroutinesApi
    private fun viewModelInit() {
        viewModel.apply {
            b = binding
            mainFragment = (requireParentFragment().requireParentFragment() as MainFragment)
            setAppBar()
            setupToggleListener()
            setSelectedButton()
        }



    }
}