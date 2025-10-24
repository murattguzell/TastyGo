package com.muratguzel.tastygo.domain.model


data class Cart(
    val cartItemId: String,
    val foodName: String,
    val foodPrice: String,
    val foodImageName: String,
    val orderQuantity: String
)