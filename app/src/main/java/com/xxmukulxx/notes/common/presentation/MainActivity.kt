package com.xxmukulxx.notes.common.presentation

import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseActivity
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.databinding.ActivityMainBinding
import com.xxmukulxx.notes.util.toast
import com.xxmukulxx.notes.util.toggleDarkMode
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var flag = false

    @Inject
    lateinit var dataStoreViewModel: DataStoreViewModel
    private lateinit var binding: ActivityMainBinding
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onLayoutCreated() {
        binding = getBinding() as ActivityMainBinding
        toggleDarkMode(dataStoreViewModel, this)
    }

    override fun onBackPressed() {
        if (flag) {
            finish()
        } else {
            toast("Press back again to exit.")
            flag = true
            Timer().schedule(2000) {
                flag = false
            }
        }
    }
}