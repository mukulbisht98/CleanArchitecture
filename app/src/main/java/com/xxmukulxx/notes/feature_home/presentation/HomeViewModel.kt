package com.xxmukulxx.notes.feature_home.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    lateinit var mainFragment: MainFragment

    private val searchBar: MutableLiveData<Boolean> =
        MutableLiveData(false)
    fun isSearchBarVisible (): LiveData<Boolean> {
        return searchBar
    }

    fun setSearchBarVisibility(value:Boolean){
        searchBar.postValue(value)
    }

    fun setAppBar() {
        mainFragment.setAppBar("")
    }

    fun handleClick(view:View){
        when(view.id){
            R.id.iv_search ->{
                setSearchBarVisibility(!searchBar.value!!)
            }
        }
    }

}