package com.xxmukulxx.notes.feature_product.data.data_cource.repository

import com.xxmukulxx.notes.feature_product.data.data_cource.ProductDao
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import com.xxmukulxx.notes.feature_product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductDataRepositoryImpl (private  val productDao: ProductDao) : ProductRepository {
    override fun getProducts(): Flow<List<ProductData>> {
        return productDao.getProducts()
    }

    override suspend fun getSingleProduct(id: Int): ProductData? {
        return productDao.getProductById(id)
    }

    override suspend fun insertProduct(data: ProductData) {
        productDao.insertProduct(data)
    }

    override suspend fun deleteProduct(id:Int) {
        productDao.deleteProduct(id)
    }

    override suspend fun updateProduct(data: ProductData) {
        productDao.update(data)
    }

    override fun searchProductFromDb(query: String): Flow<List<ProductData>> {
      return  productDao.searchDatabase(query)
    }
}