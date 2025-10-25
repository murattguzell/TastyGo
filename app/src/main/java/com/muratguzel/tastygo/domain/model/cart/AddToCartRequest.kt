package com.muratguzel.tastygo.domain.model.cart

data class AddToCartRequest(
    val userName: String,
    val foodName: String,
    val foodPrice: String,
    val foodImageName: String,
    val orderQuantity: String
)
