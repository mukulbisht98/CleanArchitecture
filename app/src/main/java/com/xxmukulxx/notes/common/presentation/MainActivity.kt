package com.xxmukulxx.notes.common.presentation

import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseActivity
import com.xxmukulxx.notes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onLayoutCreated() {
        binding = getBinding() as ActivityMainBinding
    }
}