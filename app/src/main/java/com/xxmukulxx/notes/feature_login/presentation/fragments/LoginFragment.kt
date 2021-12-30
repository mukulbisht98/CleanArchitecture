package com.xxmukulxx.notes.feature_login.presentation.fragments

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.LoginFragBinding
import com.xxmukulxx.notes.feature_login.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_login.presentation.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: LoginFragBinding

    @Inject
    lateinit var userUseCases: UserUseCases

    override fun getLayoutID(): Int {
        return R.layout.login_frag
    }

    override fun onCreateView() {
        initBindingsAndViewModel()
        observer()
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

    private fun initBindingsAndViewModel() {
        binding = getBinding() as LoginFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


}