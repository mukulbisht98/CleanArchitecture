package com.xxmukulxx.notes.feature_product.domain.use_cases

import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class SearchProductFromDb (private val repository: ProductRepository) {
    suspend operator fun invoke(query: String)  : Flow<List<ProductData>> {
        return repository.searchProductFromDb(query)
    }
}