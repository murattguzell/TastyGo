package com.muratguzel.tastygo.data.mapper

import com.muratguzel.tastygo.data.remote.dto.cart.CartListResponseDTO
import com.muratguzel.tastygo.domain.model.Cart


fun CartListResponseDTO.toCart(): List<Cart> {
    return cartItems.map {food ->
        Cart(
            cartItemId = food.cartItemId,
            foodName = food.foodName,
            foodPrice = food.foodPrice,
            orderQuantity = food.orderQuantity,
            foodImageName = food.foodImageName
        )
    }
}