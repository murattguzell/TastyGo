package com.muratguzel.tastygo.presentation.favorites.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.muratguzel.tastygo.domain.model.cart.AddToCartRequest
import com.muratguzel.tastygo.domain.model.food.Food
import com.muratguzel.tastygo.presentation.cart.viewmodel.CartViewModel
import com.muratguzel.tastygo.presentation.common.components.FoodItem
import com.muratguzel.tastygo.presentation.favorites.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    cardOnClick: (Food) -> Unit,
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    val items by favoritesViewModel.favorites.collectAsState()
    val favoriteIds by favoritesViewModel.favoriteIds.collectAsState()

    // 🔹 Search query state
    var query by remember { mutableStateOf("") }

    // 🔹 Arama filtresi (isteğe bağlı)
    val filteredItems = items.filter { food ->
        food.foodName.contains(query, ignoreCase = true)
    }
    Column(modifier = Modifier.fillMaxSize()) {

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Ara") },
            placeholder = { Text("Favorilerde Ara") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors()
        )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(filteredItems) { food ->
            FoodItem(
                title = food.foodName,
                price = food.foodPrice,
                imageUrl = food.foodImageName,
                isFavorite = favoriteIds.contains(food.foodId),
                cardOnClick = { cardOnClick(food) },
                onAddClick = { cartViewModel.addToCartSmart(AddToCartRequest("murat",food.foodName, food.foodPrice, food.foodImageName, "1")) },
                onFavoriteClick = { favoritesViewModel.removeOrInsert(food.foodId) }
            )
        }
    }
}
}
