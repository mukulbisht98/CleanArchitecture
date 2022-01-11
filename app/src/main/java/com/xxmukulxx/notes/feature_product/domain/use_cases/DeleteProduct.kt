package com.xxmukulxx.notes.feature_product.domain.use_cases

import com.xxmukulxx.notes.feature_login_signup.domain.model.InvalidUserException
import com.xxmukulxx.notes.feature_product.domain.repository.ProductRepository

class DeleteProduct (private val repository: ProductRepository) {
    @Throws(InvalidUserException::class)
    suspend operator fun invoke(res: Int) {
        res.let {
            repository.deleteProduct(res)
        }
    }
}