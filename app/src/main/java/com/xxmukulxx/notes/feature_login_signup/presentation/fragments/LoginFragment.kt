package com.xxmukulxx.notes.feature_login_signup.presentation.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.FragLoginBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_login_signup.presentation.LoginSignUpViewModel
import com.xxmukulxx.notes.util.navigateWithId
import com.xxmukulxx.notes.util.spannableString
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private val viewModel: LoginSignUpViewModel by viewModels()
    private lateinit var binding: FragLoginBinding

    @Inject
    lateinit var userUseCases: UserUseCases
    private val args: LoginFragmentArgs by navArgs()
    override val layoutResId: Int
        get() = R.layout.frag_login

    override fun onCreateView() {
        initBindingsAndViewModel()
        observer()
        init()
        viewModel.apply {
            bLogin = binding
            initAppBarLogin()
        }
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
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
            viewModel.setEmail(it.email)
            viewModel.setPass(it.pass)
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
        binding = getBinding() as FragLoginBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

}