package com.xxmukulxx.notes.feature_home.presentation

import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    lateinit var mainFragment: MainFragment

    fun setAppBar() {
        mainFragment.setAppBar("")
    }
}