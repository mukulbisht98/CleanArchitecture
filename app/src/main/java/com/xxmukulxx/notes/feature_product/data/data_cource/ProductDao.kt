package com.xxmukulxx.notes.feature_product.data.data_cource

import androidx.room.*
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM Productdata ORDER BY title ASC")
    fun getProducts(): Flow<List<ProductData>>

    @Query("SELECT * FROM Productdata WHERE id = :id")
    suspend fun getProductById(id: Int): ProductData?

    @Insert
    suspend fun insertProduct(product: ProductData)


    @Query("Update Productdata set title =:title,description=:description,type =:type,price =:price where id=:productId")
    suspend fun updateProduct(title:String,description:Int,type:String,price:String,productId:Int)

    @Update
    suspend fun update(productData: ProductData)

    @Delete
    suspend fun deleteProduct(product: ProductData)
}
