package com.xxmukulxx.notes.common.data.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.xxmukulxx.notes.util.THEME_DATA
import com.xxmukulxx.notes.util.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStore @Inject
constructor(@ApplicationContext private val context: Context) {
    suspend fun saveToLocal(data: Int) {
        context.dataStore.edit { preference ->
            preference[THEME_DATA] = data
        }
    }

    suspend fun clearData() {
        context.dataStore.edit {
            it.clear()
        }
    }

    val readFromLocal: Flow<Int> = context.dataStore.data
        .catch {
            if (this is Exception) emit(emptyPreferences())
        }.map { preference ->
           val data= preference[THEME_DATA] ?: 3
            data
        }
}