package com.xxmukulxx.notes.di

import android.app.Application
import com.xxmukulxx.notes.BuildConfig
import com.xxmukulxx.notes.MyApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBaseURL() = BuildConfig.BASE_URL

}