package com.xxmukulxx.notes.feature_product.presentation.vm

import android.widget.RatingBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
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
    var title : MutableLiveData<String> = MutableLiveData("")
    var type : MutableLiveData<String> = MutableLiveData("")
    var price : MutableLiveData<String> = MutableLiveData("")
    var description : MutableLiveData<String> = MutableLiveData("new I-Mac pro 2022 offers latest ai neural engine M2 Pro Max chip with 8k display.")
    var quantity : MutableLiveData<String> = MutableLiveData("")
    val product: ProductData = ProductData(
        1,
        "I-Mac Pro 2022",
        "Electronics",
        "new I-Mac pro 2022 offers latest ai neural engine M2 Pro Max chip with 8k display.",
        2000, 999999f, 4f,
        "https://picsum.photos/250/200"
    )

    val ratingText: MutableLiveData<String> = MutableLiveData(product.rating.toString())
    val priceTag: MutableLiveData<String> = MutableLiveData("₹${product.price}")


    fun onRatingChanged(rb: RatingBar, rating: Float, selected: Boolean) {
        ratingText.postValue("$rating")
    }


    fun setAppBar() {
        b.appBar.ivBack.setOnClickListener {
            b.root.navigateBack()
        }
    }

    fun setData (id:Int){
        viewModelScope.launch {
            val productData = productUseCases.getSingleProduct(id)
            title.postValue(productData?.title)
            type.postValue(productData?.type)
            quantity.postValue(productData?.quantity.toString())
            price.postValue(productData?.price.toString())
        }
    }

}