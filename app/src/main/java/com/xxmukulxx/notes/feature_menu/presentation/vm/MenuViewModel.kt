package com.xxmukulxx.notes.feature_menu.presentation.vm

import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : BaseViewModel() {

    lateinit var mainFragment: MainFragment

    fun setAppBar() {
        mainFragment.setAppBar(getString(R.string.menu))
    }
}