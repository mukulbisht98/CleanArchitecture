package com.xxmukulxx.notes.feature_profile.presentation

import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseFragment
import com.xxmukulxx.notes.databinding.ProfileFragBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment(override val layoutResId: Int = R.layout.profile_frag) : BaseFragment() {

    private val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var userUseCases: UserUseCases
    private lateinit var binding: ProfileFragBinding

    override fun onCreateView() {
        initBindingsAndViewModel()
    }

    private fun initBindingsAndViewModel() {
        binding = getBinding() as ProfileFragBinding
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.bnLogout.setOnClickListener {
            viewModel.viewModelScope.launch {
                userUseCases.deleteUser()
            }
            showToast("Logged out.")
            requireActivity().finish()
        }
    }
}