package com.xxmukulxx.notes.feature_main.presentation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.FragMainBinding
import com.xxmukulxx.notes.util.hide
import com.xxmukulxx.notes.util.show

class MainFragment(override val layoutResId: Int = R.layout.frag_main) : BaseFragment() {

    private lateinit var binding: FragMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView() {
        initBindingsAndViewModel()
        setUpHomeNavigation()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as FragMainBinding
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

    fun setAppBar(title: String) {
        if (title.isBlank()) {
            return binding.appBar.appBar.hide()
        }
        binding.appBar.appBar.show()
        binding.appBar.tvTitle.text = title
        binding.appBar.ivBack.hide()
        binding.appBar.ivInfo.hide()
    }
}