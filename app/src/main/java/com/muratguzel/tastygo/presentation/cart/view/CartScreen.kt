package com.muratguzel.tastygo.presentation.cart.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.muratguzel.tastygo.domain.model.Cart
import com.muratguzel.tastygo.presentation.cart.components.CartItem

private val fakeCartList = listOf(
    Cart(
        cartItemId = "1",
        foodName = "Pizza Margherita",
        foodPrice = "85.0",
        foodImageName = "https://example.com/pizza.jpg",
        orderQuantity = "1"
    ),
    Cart(
        cartItemId = "2",
        foodName = "Cheeseburger",
        foodPrice = "65.0",
        foodImageName = "https://example.com/burger.jpg",
        orderQuantity = "2"
    ),
    Cart(
        cartItemId = "3",
        foodName = "Spaghetti Bolognese",
        foodPrice = "70.0",
        foodImageName = "https://example.com/spaghetti.jpg",
        orderQuantity = "1"
    )
)

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(fakeCartList) { item ->
            CartItem(item)
        }
    }
}