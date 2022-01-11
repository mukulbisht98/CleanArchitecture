package com.xxmukulxx.notes.feature_product.domain.use_cases

import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProducts (private val repository: ProductRepository) {
    operator fun invoke(): Flow<List<ProductData>> {
        return repository.getProducts()
    }
}