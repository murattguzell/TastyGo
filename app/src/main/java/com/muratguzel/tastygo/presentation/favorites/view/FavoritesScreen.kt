package com.muratguzel.tastygo.presentation.favorites.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.muratguzel.tastygo.domain.model.Food
import com.muratguzel.tastygo.presentation.common.components.FoodItem
import com.muratguzel.tastygo.presentation.favorites.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    favoritesViewModel: FavoritesViewModel = hiltViewModel(),
    cardOnClick: (Food) -> Unit
) {
    val items by favoritesViewModel.favorites.collectAsState()
    val favoriteIds by favoritesViewModel.favoriteIds.collectAsState()



    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items) { food ->
            FoodItem(
                title = food.foodId,
                price = food.foodPrice,
                imageUrl = food.foodImageName,
                isFavorite = favoriteIds.contains(food.foodId),
                cardOnClick = { cardOnClick(food) },
                onAddClick = { /* sepete ekle */ },
                onFavoriteClick = { favoritesViewModel.removeOrInsert(food.foodId) }
            )
        }
    }
}