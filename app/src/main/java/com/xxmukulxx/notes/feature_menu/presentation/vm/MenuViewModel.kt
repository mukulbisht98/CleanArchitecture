package com.xxmukulxx.notes.feature_menu.presentation.vm

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.common.data.data_store.DataStore
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.databinding.MenuFragBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.getString
import com.xxmukulxx.notes.util.toggleDarkMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val userUseCases: UserUseCases , private  val dataStoreViewModel: DataStoreViewModel) : BaseViewModel() {

    lateinit var b: MenuFragBinding
    lateinit var mainFragment: MainFragment
    private var dataStore: DataStore? = null

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
        viewModelScope.launch {
            dataStoreViewModel.saveToLocal("Yes")
        }
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