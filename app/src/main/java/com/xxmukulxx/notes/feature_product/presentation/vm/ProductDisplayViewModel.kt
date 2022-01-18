package com.xxmukulxx.notes.feature_product.presentation.vm

import android.view.View
import android.widget.RatingBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.FragProductDisplayBinding
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.use_cases.ProductUseCases
import com.xxmukulxx.notes.util.navigateBack
import com.xxmukulxx.notes.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDisplayViewModel @Inject constructor(private val productUseCases: ProductUseCases) :
    BaseViewModel() {

    lateinit var b: FragProductDisplayBinding
    var title: MutableLiveData<String> = MutableLiveData("")
    var type: MutableLiveData<String> = MutableLiveData("")
    var price: MutableLiveData<String> = MutableLiveData("")
    var description: MutableLiveData<String> = MutableLiveData("")
    var quantity: MutableLiveData<String> = MutableLiveData("")
    var imgUrl: MutableLiveData<String> = MutableLiveData("")
    var productLiveData: MutableLiveData<ProductData> = MutableLiveData(null)

    val ratingText: MutableLiveData<String> =
        MutableLiveData("")
    val priceTag: MutableLiveData<String> = MutableLiveData("")


    fun onRatingChanged(rb: RatingBar, rating: Float, selected: Boolean) {
        ratingText.postValue("$rating")
    }

    fun setAppBar() {
        b.appBar.ivBack.setOnClickListener {
            b.root.navigateBack()
        }
    }

    fun handleClicks(v: View) {
        when (v.id) {
            R.id.bnAddToCart -> {
                toast("Add to cart yet to implement.")
            }
        }
    }

    fun setData(id: Int) {
        viewModelScope.launch {
            productUseCases.getSingleProduct(id)?.let { it ->
                productLiveData.postValue(it)
                title.postValue(it.title)
                type.postValue(it.type)
                quantity.postValue(it.quantity.toString())
                price.postValue(it.price.toString())
                imgUrl.postValue(it.imgUrl)
                ratingText.postValue("${it.rating}")
                priceTag.postValue("₹${it.price}")
            }
        }
    }

}