package com.muratguzel.tastygo.data.repository

import com.muratguzel.tastygo.data.mapper.toAddToCartRequestDTO
import com.muratguzel.tastygo.data.mapper.toAddToCartResult
import com.muratguzel.tastygo.data.mapper.toDeleteFromCartRequestDTO
import com.muratguzel.tastygo.data.mapper.toDeleteFromCartResult
import com.muratguzel.tastygo.data.mapper.toGetCartItemsRequestDTO
import com.muratguzel.tastygo.data.mapper.toGetCartResult
import com.muratguzel.tastygo.data.remote.CartAPI
import com.muratguzel.tastygo.domain.model.cart.AddToCartRequest
import com.muratguzel.tastygo.domain.model.cart.AddToCartResult
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartRequest
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartResult
import com.muratguzel.tastygo.domain.model.cart.GetCartRequest
import com.muratguzel.tastygo.domain.model.cart.GetCartResult
import com.muratguzel.tastygo.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val api: CartAPI
) : CartRepository {

    override suspend fun addCart(request: AddToCartRequest): AddToCartResult {
        // 1. Domain model → DTO
        val dto = request.toAddToCartRequestDTO()

        // 2. API çağrısı
        val response = api.addToCart(
            dto.foodName,
            dto.foodImageName,
            dto.foodPrice,
            dto.orderQuantity,
            dto.userName
        )

        // 3. DTO → Domain
        return response.toAddToCartResult()

    }

    override suspend fun getCart(request: GetCartRequest): GetCartResult {
        val dto = request.toGetCartItemsRequestDTO()
        val response = api.getCartItems(dto.userName)
        return response.toGetCartResult()
    }

    override suspend fun deleteCart(request: DeleteFromCartRequest): DeleteFromCartResult {
        val dto = request.toDeleteFromCartRequestDTO()
        val response = api.deleteFromCart(dto.userName, dto.cartItemId.toInt())
        return response.toDeleteFromCartResult()
    }
}
