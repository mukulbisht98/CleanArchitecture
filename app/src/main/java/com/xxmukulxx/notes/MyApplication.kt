package com.xxmukulxx.notes

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BASE_URL = BuildConfig.BASE_URL
        context = this@MyApplication

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        var BASE_URL = ""
    }
}