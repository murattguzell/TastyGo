package com.muratguzel.tastygo.presentation.foods.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muratguzel.tastygo.presentation.common.components.FoodItem
import com.muratguzel.tastygo.presentation.foods.viewmodel.FoodViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.muratguzel.tastygo.domain.model.Food
import com.muratguzel.tastygo.presentation.favorites.viewmodel.FavoritesViewModel


@Composable
fun FoodListScreen(
    foodViewModel: FoodViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel =hiltViewModel(),
    cardOnClick: (Food) -> Unit,
    addOnClick: (Food) -> Unit
) {
    val state = foodViewModel.foodState.value

    val favoriteIds by favoritesViewModel.favoriteIds.collectAsState()

    val gridState = rememberLazyGridState()

    var query by rememberSaveable { mutableStateOf("") }

    val foods = remember(query, state.food) {
        if (query.isBlank()) state.food
        else state.food.filter { it.foodName.contains(query, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Ara") },
            placeholder = { Text("Yemek Ara") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors()
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
        ) {

            items(foods) { food ->
                val isFav = favoriteIds.contains(food.foodId)

                FoodItem(
                    title = food.foodName,
                    price = food.foodPrice,
                    isFavorite = isFav,
                    imageUrl = food.foodImageName,
                    onAddClick = { addOnClick },
                    cardOnClick = { cardOnClick(food) },
                    onFavoriteClick = { favoritesViewModel.onFavoriteClick(food) }
                )
                Log.e("FoodImage", food.foodImageName)
            }
        }
    }
}

