package com.xxmukulxx.notes

import android.annotation.SuppressLint
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object AppContext {
        lateinit var context: Application
    }

    init {
        context = this
    }

    @SuppressLint("WrongConstant")
    override fun onCreate() {
        super.onCreate()

    }

}