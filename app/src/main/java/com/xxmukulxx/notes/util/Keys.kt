package com.xxmukulxx.notes.util

import androidx.datastore.preferences.core.intPreferencesKey


const val DATA_STORE_NAME = "data_store"
//key name
val THEME_DATA by lazy { intPreferencesKey("THEME_DATA") }