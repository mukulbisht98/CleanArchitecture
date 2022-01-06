package com.xxmukulxx.notes.util

import androidx.datastore.preferences.core.stringPreferencesKey


const val DATA_STORE_NAME = "data_store"
//key name
val THEME_DATA by lazy { stringPreferencesKey("THEME_DATA") }