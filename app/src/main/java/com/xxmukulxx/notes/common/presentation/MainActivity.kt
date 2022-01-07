package com.xxmukulxx.notes.common.presentation

import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseActivity
import com.xxmukulxx.notes.databinding.ActivityMainBinding
import com.xxmukulxx.notes.feature_home.presentation.HomeFragment
import com.xxmukulxx.notes.util.navigateBack
import com.xxmukulxx.notes.util.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var flag = false

    private lateinit var binding: ActivityMainBinding
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onLayoutCreated() {
        binding = getBinding() as ActivityMainBinding
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