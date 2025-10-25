package com.muratguzel.tastygo.domain.model.cart

data class DeleteFromCartRequest(
    val userName: String,
    val cartItemId: String
)
