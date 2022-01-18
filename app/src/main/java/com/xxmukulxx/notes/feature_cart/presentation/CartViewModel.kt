package com.xxmukulxx.notes.feature_cart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.feature_main.presentation.MainFragment
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.use_cases.ProductUseCases
import com.xxmukulxx.notes.util.getString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val productUseCases: ProductUseCases) : BaseViewModel() {
    lateinit var mainFragment: MainFragment

    lateinit var productListLiveData: LiveData<List<ProductData>>

    init {
        viewModelScope.launch {
            productListLiveData = productUseCases.getProducts().asLiveData(this.coroutineContext)
        }
    }

    fun setAppBar() {
        mainFragment.setAppBar(getString(R.string.cart))
    }
}