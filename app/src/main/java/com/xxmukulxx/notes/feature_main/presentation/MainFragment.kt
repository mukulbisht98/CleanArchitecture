package com.xxmukulxx.notes.feature_main.presentation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.MainFragBinding

class MainFragment(override val layoutResId: Int = R.layout.main_frag) : BaseFragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: MainFragBinding
    override fun onCreateView() {
        initBindingsAndViewModel()
        setUpHomeNavigation()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as MainFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setUpHomeNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_home_fragment) as NavHostFragment
        binding.bottomNav.setupWithNavController(
            navHostFragment.navController
        )
    }
}