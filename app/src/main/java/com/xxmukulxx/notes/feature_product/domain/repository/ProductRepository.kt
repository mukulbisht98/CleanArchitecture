package com.xxmukulxx.notes.feature_product.domain.repository

import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import kotlinx.coroutines.flow.Flow


interface ProductRepository {

    fun getProducts(): Flow<List<ProductData>>

    suspend fun getSingleProduct(id: Int): ProductData?

    suspend fun insertProduct(data: ProductData)

    suspend fun deleteProduct(id: Int)

    suspend fun updateProduct(data: ProductData)

    fun searchProductFromDb(query:String) : Flow<List<ProductData>>
}