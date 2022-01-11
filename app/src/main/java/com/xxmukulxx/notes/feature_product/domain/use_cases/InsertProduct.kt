package com.xxmukulxx.notes.feature_product.domain.use_cases

import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.repository.ProductRepository

class InsertProduct(private val repository: ProductRepository) {
    suspend operator fun invoke(res: ProductData) {
        repository.insertProduct(res)
    }
}