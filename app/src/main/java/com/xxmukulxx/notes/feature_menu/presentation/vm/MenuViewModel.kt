package com.xxmukulxx.notes.feature_menu.presentation.vm

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.databinding.MenuFragBinding
import com.xxmukulxx.notes.feature_login_signup.domain.use_cases.UserUseCases
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
    private val dataStoreViewModel: DataStoreViewModel
) : BaseViewModel() {

    lateinit var b: MenuFragBinding
    lateinit var mainFragment: MainFragment

    private val isLightModeSelected: MutableLiveData<Boolean> =
        MutableLiveData(false)
    private val isNightModeSelected: MutableLiveData<Boolean> =
        MutableLiveData(false)
    private val isSystemModeModeSelected: MutableLiveData<Boolean> =
        MutableLiveData(false)


    fun isLightModeSelected(): LiveData<Boolean> {
        return isLightModeSelected
    }

    fun setLightMode() {
        isLightModeSelected.postValue(true)
        isNightModeSelected.postValue(false)
        isSystemModeModeSelected.postValue(false)
    }

    fun isNightModeSelected(): LiveData<Boolean> {
        return isNightModeSelected
    }

    fun setNightMode() {
        isNightModeSelected.postValue(true)
        isLightModeSelected.postValue(false)
        isSystemModeModeSelected.postValue(false)
    }

    fun isSystemModeSelected(): LiveData<Boolean> {
        return isSystemModeModeSelected
    }

    fun setSystemMode() {
        isLightModeSelected.postValue(false)
        isNightModeSelected.postValue(false)
        isSystemModeModeSelected.postValue(true)
    }

    fun setAppBar() {
        mainFragment.setAppBar(getString(R.string.menu))
    }

    fun setSelectedButton() {
        CoroutineScope(Dispatchers.Main).launch {
            launch {
                dataStoreViewModel.readFromLocal.collect {
                    when (it) {
                        1 -> {
                            setLightMode()
                        }
                        2 -> {
                            setNightMode()
                        }
                        else -> {
                            setSystemMode()
                        }
                    }
                }
            }
        }
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

    @InternalCoroutinesApi
    fun setupToggleListener() {
        b.mbToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked)
                when (checkedId) {
                    R.id.mbLight -> dataStoreViewModel.saveToLocal(1)
                    R.id.mbDark -> dataStoreViewModel.saveToLocal(2)
                    R.id.mbSystem -> dataStoreViewModel.saveToLocal(3)
                }
        }
    }
}