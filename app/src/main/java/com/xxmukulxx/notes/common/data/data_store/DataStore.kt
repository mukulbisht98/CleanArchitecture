package com.xxmukulxx.notes.common.data.data_store

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.google.gson.Gson
import com.xxmukulxx.notes.MyApplication.AppContext.appContext
import com.xxmukulxx.notes.util.THEME_DATA
import com.xxmukulxx.notes.util.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStore @Inject
constructor() {
    suspend fun saveToLocal(data: String) {
        appContext.dataStore.edit { preference ->
            preference[THEME_DATA] = Gson().toJson(data)
        }
    }

    val readFromLocal: Flow<String> = appContext.dataStore.data
        .catch {
            if (this is Exception) {
                emit(emptyPreferences())
            }
        }.map { preference ->
            val data = preference[THEME_DATA] ?: ""
            data
        }

}