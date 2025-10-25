package com.muratguzel.tastygo.domain.repository

import com.muratguzel.tastygo.domain.model.cart.Cart
import com.muratguzel.tastygo.domain.model.cart.AddToCartRequest
import com.muratguzel.tastygo.domain.model.cart.AddToCartResult
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartRequest
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartResult
import com.muratguzel.tastygo.domain.model.cart.GetCartRequest
import com.muratguzel.tastygo.domain.model.cart.GetCartResult
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addCart(request: AddToCartRequest): AddToCartResult
    suspend fun getCart(request: GetCartRequest): GetCartResult
    suspend fun deleteCart(request: DeleteFromCartRequest): DeleteFromCartResult
}