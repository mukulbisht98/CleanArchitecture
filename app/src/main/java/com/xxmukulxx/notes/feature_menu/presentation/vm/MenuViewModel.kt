package com.xxmukulxx.notes.feature_menu.presentation.vm

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.MenuFragBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.getString
import com.xxmukulxx.notes.util.toggleDarkMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val userUseCases: UserUseCases) : BaseViewModel() {

    lateinit var b: MenuFragBinding
    lateinit var mainFragment: MainFragment

    fun setAppBar() {
        mainFragment.setAppBar(getString(R.string.menu))
    }

    fun handleClicks(v: View) {
        when (v.id) {
            R.id.bnLogout -> {
                viewModelScope.launch {
                    userUseCases.deleteUser()
                }
                logout()
            }
        }
    }

    private fun logout() {
        mainFragment.findNavController().navigate(R.id.action_global_loginFragment)
        showToast("Logged out.")
    }

    fun setupToggleListener() {
        b.mbToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked)
                when (checkedId) {
                    R.id.mbDark -> toggleDarkMode(true)
                    R.id.mbLight -> toggleDarkMode(false)
                    R.id.mbSystem -> toggleDarkMode(null)
                }
        }
    }
}