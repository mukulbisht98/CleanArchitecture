package com.xxmukulxx.notes.feature_menu.presentation.vm

import android.view.View
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.databinding.FragMenuBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.getString
import com.xxmukulxx.notes.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val dataStoreViewModel: DataStoreViewModel
) : BaseViewModel() {

    lateinit var b: FragMenuBinding
    lateinit var mainFragment: MainFragment

    fun setAppBar() {
        mainFragment.setAppBar(getString(R.string.menu))
    }

    fun setupToggleListener() {
        viewModelScope.launch {
            dataStoreViewModel.readFromLocal.collect {
                when (it) {
                    1 -> b.mbToggle.check(b.mbLight.id)
                    2 -> b.mbToggle.check(b.mbDark.id)
                    3 -> b.mbToggle.check(b.mbSystem.id)
                }
            }
        }
        b.mbToggle.addOnButtonCheckedListener { _, checkedId, checked ->
            if (checked)
                when (checkedId) {
                    R.id.mbLight -> dataStoreViewModel.saveToLocal(1)
                    R.id.mbDark -> dataStoreViewModel.saveToLocal(2)
                    R.id.mbSystem -> dataStoreViewModel.saveToLocal(3)
                }
        }
    }

    fun handleClicks(v: View) {
        when (v.id) {
            R.id.bnLogout -> {
                viewModelScope.launch {
                    userUseCases.deleteUser()
                    dataStoreViewModel.clearData()
                    logout()
                }
            }
        }
    }

    private fun logout() {
        mainFragment.findNavController().navigate(R.id.action_global_loginFragment)
        toast("Logged out.")
    }
}