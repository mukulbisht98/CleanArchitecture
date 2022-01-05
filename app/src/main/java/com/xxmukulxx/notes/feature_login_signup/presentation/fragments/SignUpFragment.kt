package com.xxmukulxx.notes.feature_login_signup.presentation.fragments

import androidx.fragment.app.viewModels
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.SignupFragBinding
import com.xxmukulxx.notes.feature_login_signup.presentation.LoginSignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment() {

    private val viewModel: LoginSignUpViewModel by viewModels()
    private lateinit var binding: SignupFragBinding

    override val layoutResId: Int
        get() = R.layout.signup_frag

    override fun onCreateView() {
        initBindingsAndViewModel()
        observer()
        viewModel.apply {
            bSignUp = binding
            initAppBarSignUp()
        }
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
        binding = getBinding() as SignupFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}