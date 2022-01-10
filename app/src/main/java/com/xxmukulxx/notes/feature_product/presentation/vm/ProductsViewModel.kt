package com.xxmukulxx.notes.feature_product.presentation.vm

import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.MutableLiveData
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseViewModel
import com.xxmukulxx.notes.databinding.FragAddProductBinding
import com.xxmukulxx.notes.util.getString
import com.xxmukulxx.notes.util.navigateBack
import com.xxmukulxx.notes.util.setImg
import com.xxmukulxx.notes.util.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor() : BaseViewModel() {
    lateinit var b: FragAddProductBinding

    private var tempProductTitle: MutableLiveData<String> = MutableLiveData("")
    private var tempProductDescription: MutableLiveData<String> = MutableLiveData("")
    private var tempProductPrice: MutableLiveData<String> = MutableLiveData("")
    private var tempProductType: MutableLiveData<String> = MutableLiveData("")
    private var tempProductQuantity: MutableLiveData<String> = MutableLiveData("")
    private var tempImgUrl: MutableLiveData<String> = MutableLiveData("")

    fun setAppBar() {
        b.appBar.tvTitle.text = getString(R.string.add_product)
        b.appBar.ivBack.setOnClickListener {
            it.navigateBack()
        }
        b.appBar.ivInfo.setOnClickListener {
            toast("You can add products here, these products will show up in the home page.")
        }
    }

    fun setupItems() {
        val menu = PopupMenu(b.tilSelectProductType.context, b.etSelectProductType).apply {
            menuInflater.inflate(R.menu.add_product_type, menu)
            setOnMenuItemClickListener { item ->
                b.etSelectProductType.setText(item.title)
                true
            }
        }
        b.tilSelectProductType.setEndIconOnClickListener {
            menu.show()
            it.isEnabled = true
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
            R.id.ivAddProductImage -> {
                getPhotoFromGallery()
            }
            R.id.bnAddProduct -> {
                // product adding not implemented yet
                toast("Product added: ${tempProductTitle.value} ${tempProductPrice.value} ${tempProductType.value} ${tempImgUrl.value}")
            }
        }
    }

    private fun getPhotoFromGallery() {
        // get photo from gallery -> upload to server -> upload to database -> Show here
        toast("Get photo from gallery not implemented, getting image from glide.")
        tempImgUrl.postValue("https://picsum.photos/300/300")
        b.ivAddProductImage.setImg(tempImgUrl.value)
    }
}