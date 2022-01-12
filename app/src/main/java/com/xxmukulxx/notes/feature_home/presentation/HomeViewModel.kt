package com.xxmukulxx.notes.feature_home.presentation

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
class HomeViewModel @Inject constructor(productUseCases: ProductUseCases) : BaseViewModel() {
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

    fun setSearchBarVisibility(value: Boolean) {
        searchBar.postValue(value)
    }

    fun setAppBar() {
        mainFragment.setAppBar("")
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