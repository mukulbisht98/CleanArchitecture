package com.xxmukulxx.notes.feature_login_signup_with_api.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.LoginFragBinding
import com.xxmukulxx.notes.feature_login_signup_with_api.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_login_signup_with_api.presentation.LoginSignUpViewModel
import com.xxmukulxx.notes.util.navigateWithId
import com.xxmukulxx.notes.util.spannableString
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private val viewModel: LoginSignUpViewModel by viewModels()
    private lateinit var binding: LoginFragBinding

    @Inject
    lateinit var userUseCases: UserUseCases
    private val args: LoginFragmentArgs by navArgs()
    override val layoutResId: Int
        get() = R.layout.login_frag

    override fun onCreateView() {
        initBindingsAndViewModel()
        observer()
        init()

    }

    private fun init() {
        binding.tvSignUp.run {
            spannableString(R.string.don_t_have_account_signup_here, 20, 31) {
                navigateWithId(R.id.action_loginFragment_to_signUpFragment)
            }
        }

        args.let {
            binding.etEmail.setText(it.email)
            binding.etPassword.setText(it.pass)
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
        binding = getBinding() as LoginFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }


}