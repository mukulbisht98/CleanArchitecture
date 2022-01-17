package com.xxmukulxx.notes.feature_home.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.common.presentation.adapter.RecyclerAdapter
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.use_cases.ProductUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val productUseCases: ProductUseCases) : BaseViewModel() {
    lateinit var mainFragment: MainFragment
    lateinit var productListLiveData: LiveData<List<ProductData>>

    init {
        viewModelScope.launch {
            productListLiveData = productUseCases.getProducts().asLiveData(this.coroutineContext)
        }
    }

    private val searchBar: MutableLiveData<Boolean> =
        MutableLiveData(false)

    fun isSearchBarVisible(): LiveData<Boolean> {
        return searchBar
    }

    private fun setSearchBarVisibility(value: Boolean) {
        searchBar.postValue(value)
    }

    fun setAppBar() {
        mainFragment.setAppBar("")
    }

    fun onSearchQuery(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            viewModelScope.launch {
                val searchList = productUseCases.searchProductFromDb(s.toString().trim().lowercase()).asLiveData(this.coroutineContext)
                Log.e("SearchData", "onSearchQuery: ${searchList.value?.size}", )

            }

    }
    fun handleClick(view: View) {
        when (view.id) {
            R.id.iv_search -> {
                setSearchBarVisibility(!searchBar.value!!)
            }
            R.id.ivAddProduct -> {
                mainFragment.findNavController().navigate(R.id.action_mainFragment_to_addProduct)
            }
        }
    }
}