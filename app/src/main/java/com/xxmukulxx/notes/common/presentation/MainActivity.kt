package com.xxmukulxx.notes.common.presentation

import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseActivity
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.databinding.ActivityMainBinding
import com.xxmukulxx.notes.util.toggleDarkMode
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {


    @Inject
    lateinit var dataStoreViewModel: DataStoreViewModel
    private lateinit var binding: ActivityMainBinding
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onLayoutCreated() {
        binding = getBinding() as ActivityMainBinding
        toggleDarkMode(dataStoreViewModel, this)
    }

}