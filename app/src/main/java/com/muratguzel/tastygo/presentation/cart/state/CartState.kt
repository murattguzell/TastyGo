package com.muratguzel.tastygo.presentation.cart.state

import com.muratguzel.tastygo.domain.model.cart.Cart

data class CartState(
    val isLoading: Boolean = false,
    val food: List<Cart> = emptyList(),
    val error: String = ""
)