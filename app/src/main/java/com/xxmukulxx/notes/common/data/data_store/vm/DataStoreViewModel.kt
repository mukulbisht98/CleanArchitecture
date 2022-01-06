package com.xxmukulxx.notes.common.data.data_store.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.notes.common.data.data_store.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(private val datastoreSetting: DataStore) : ViewModel() {


    fun saveToLocal(modeType: String) = viewModelScope.launch {
        datastoreSetting.saveToLocal(modeType)
    }

    val readFromLocal = datastoreSetting.readFromLocal
}