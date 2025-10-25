package com.muratguzel.tastygo.domain.model.cart


data class GetCartResult(
    val cartItems: List<Cart>,
    val success: Boolean
)