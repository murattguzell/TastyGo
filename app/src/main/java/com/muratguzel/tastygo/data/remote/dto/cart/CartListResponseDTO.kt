package com.muratguzel.tastygo.data.remote.dto.cart

import com.google.gson.annotations.SerializedName

data class CartListResponseDTO(
    @SerializedName("sepet_yemekler")
    val cartItems: List<CartItemDTO>?,
    @SerializedName("success")
    val success: Int
)