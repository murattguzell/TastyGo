package com.muratguzel.tastygo.data.mapper

import com.muratguzel.tastygo.data.remote.dto.cart.AddToCartRequestDTO
import com.muratguzel.tastygo.data.remote.dto.cart.AddToCartResponseDTO
import com.muratguzel.tastygo.data.remote.dto.cart.CartListResponseDTO
import com.muratguzel.tastygo.data.remote.dto.cart.GetCartItemsRequestDTO
import com.muratguzel.tastygo.data.remote.dto.cart.DeleteFromCartRequestDTO
import com.muratguzel.tastygo.data.remote.dto.cart.DeleteFromCartResponseDTO
import com.muratguzel.tastygo.domain.model.cart.AddToCartRequest
import com.muratguzel.tastygo.domain.model.cart.AddToCartResult
import com.muratguzel.tastygo.domain.model.cart.Cart
import com.muratguzel.tastygo.domain.model.cart.GetCartRequest
import com.muratguzel.tastygo.domain.model.cart.GetCartResult
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartRequest
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartResult
import com.muratguzel.tastygo.util.Constant.BASE_IMAGE_URL


fun AddToCartRequest.toAddToCartRequestDTO(): AddToCartRequestDTO {
    return AddToCartRequestDTO(
        foodName = foodName,
        foodImageName = foodImageName,
        foodPrice = foodPrice.toIntOrNull() ?: 0,
        orderQuantity = orderQuantity.toIntOrNull() ?: 1,
        userName = userName
    )
}

fun AddToCartResponseDTO.toAddToCartResult(): AddToCartResult {
    return AddToCartResult(
        success = success == 1,
        message = message
    )
}

fun GetCartRequest.toGetCartItemsRequestDTO(): GetCartItemsRequestDTO {
    return GetCartItemsRequestDTO(userName = userName)
}

fun CartListResponseDTO.toGetCartResult(): GetCartResult {
    return GetCartResult(
        cartItems = (cartItems ?: emptyList()).map { dto ->
            Cart(
                cartItemId = dto.cartItemId,
                foodName = dto.foodName,
                foodPrice = dto.foodPrice,
                orderQuantity = dto.orderQuantity,
                foodImageName = dto.foodImageName
            )
        },
        success = success == 1
    )
}

fun DeleteFromCartRequest.toDeleteFromCartRequestDTO(): DeleteFromCartRequestDTO {
    return DeleteFromCartRequestDTO(
        userName = userName,
        cartItemId = cartItemId.toIntOrNull() ?: 0
    )
}

fun DeleteFromCartResponseDTO.toDeleteFromCartResult(): DeleteFromCartResult {
    return DeleteFromCartResult(
        success = success == 1,
        message = message
    )
}
