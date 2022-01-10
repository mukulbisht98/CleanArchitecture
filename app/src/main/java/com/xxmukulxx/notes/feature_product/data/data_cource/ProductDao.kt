package com.xxmukulxx.notes.feature_product.data.data_cource

import androidx.room.*
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM productdata")
    fun getProducts(): Flow<List<ProductData>>

    @Query("SELECT * FROM Productdata WHERE id = :id")
    suspend fun getProductById(id: Int): ProductData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductData)

    @Delete
    suspend fun deleteProduct(product: ProductData)
}
