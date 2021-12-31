package com.xxmukulxx.notes.feature_login_signup_with_api.presentation.fragments

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.LoginFragBinding
import com.xxmukulxx.notes.databinding.SignupFragBinding
import com.xxmukulxx.notes.feature_login_signup_with_api.presentation.LoginSignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {

    private val signUpViewModel: LoginSignUpViewModel by viewModels()
    private lateinit var binding: SignupFragBinding

    override val layoutResId: Int
        get() = R.layout.signup_frag

    override fun onCreateView() {
        initBindingsAndViewModel()
        observer()
    }

    private fun observer() {
        signUpViewModel.getIsLoadingLiveData().observe(viewLifecycleOwner, {
            if (it) {
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as SignupFragBinding
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

}