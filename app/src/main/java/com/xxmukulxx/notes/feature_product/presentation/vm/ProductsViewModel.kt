package com.xxmukulxx.notes.feature_product.presentation.vm

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.*
import com.xxmukulxx.notes.MyApplication
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.FragAddProductBinding
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.use_cases.ProductUseCases
import com.xxmukulxx.notes.util.*
import com.xxmukulxx.notes.util.validation.Validation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productUseCases: ProductUseCases,) : BaseViewModel() {
    lateinit var b: FragAddProductBinding

    private var tempProductTitle: MutableLiveData<String> = MutableLiveData("")
    private var tempProductDescription: MutableLiveData<String> = MutableLiveData("")
    private var tempProductPrice: MutableLiveData<String> = MutableLiveData("")
    private var tempProductType: MutableLiveData<String> = MutableLiveData("")
    private var tempProductQuantity: MutableLiveData<String> = MutableLiveData("")
    private var tempImgUrl: MutableLiveData<String> = MutableLiveData("")
    public var productList: LiveData<List<ProductData>>?=null

    private val menu: PopupMenu by lazy {
        PopupMenu(b.tilSelectProductType.context, b.etSelectProductType).apply {
            menuInflater.inflate(R.menu.add_product_type, menu)
            setOnMenuItemClickListener { item ->
                b.etSelectProductType.setText(item.title)
                true
            }
        }
    }

    fun setAppBar() {
        b.appBar.tvTitle.text = getString(R.string.add_product)
        b.appBar.ivBack.setOnClickListener {
            it.navigateBack()
        }
        b.appBar.ivInfo.setOnClickListener {
//            viewModelScope.launch {
                productList  = productUseCases.getProduct()?.asLiveData()




//            toast("You can add products here, these products will show up in the home page.")
        }
    }

    fun setupItems() {
        b.tilSelectProductType.setEndIconOnClickListener {
            menu.show()
        }
    }

    fun onProductTitleChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            tempProductTitle.postValue(s.trim().toString())
    }

    fun onProductPriceChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            tempProductPrice.postValue(s.trim().toString())
    }

    fun onProductTypeChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            tempProductType.postValue(s.trim().toString())
    }

    fun onProductQuantityChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.toString().isNotBlank())
            tempProductQuantity.postValue(s.trim().toString())
    }

     fun handleClicks(v: View) {
        when (v.id) {
            R.id.etSelectProductType -> {
                menu.show()
            }
            R.id.ivAddProductImage -> {
                getPhotoFromGallery()
            }
            R.id.bnAddProduct -> {
                if (checkValidation(MyApplication.appContext)){
                    viewModelScope.launch {
                        productUseCases.insertProduct(ProductData(title = tempProductTitle.value.toString(), type = tempProductType.value.toString(),
                            quantity = tempProductQuantity.value!!.toInt(), price = tempProductPrice.value!!.toFloat(), description = null))
                    }

                }
            }
        }
    }

    private fun getPhotoFromGallery() {
        // get photo from gallery -> upload to server -> upload to database -> Show here
        toast("Get photo from gallery not implemented, getting image from glide.")
        tempImgUrl.postValue("https://picsum.photos/300/300")
        b.ivAddProductImage.setImg(tempImgUrl.value)
    }
    private fun checkValidation(context: Context): Boolean {
        val validation = Validation().apply {
            isEmpty(tempProductType.value?.trim(), context.getString(R.string.select_product_type))
            isEmpty(tempProductTitle.value?.trim(), context.getString(R.string.enter_product_title))
            isEmpty(tempProductPrice.value?.trim(), context.getString(R.string.enter_product_price))
            isEmpty(tempProductQuantity.value?.trim(), context.getString(R.string.enter_product_quantity))
        }

        return validation.isValid()
    }
}