package com.muratguzel.tastygo.presentation.foods.view

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muratguzel.tastygo.presentation.foods.components.FoodItem
import com.muratguzel.tastygo.presentation.foods.viewmodel.FoodViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FoodListScreen(
    foodViewModel: FoodViewModel =  hiltViewModel()
) {
    val state = foodViewModel.foodState.value

    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(12.dp)
    ) {

        items(state.food) { food ->
            FoodItem(
                title = food.foodName,
                price = food.foodPrice,
                imageUrl = food.foodImageName,
                onAddClick = { /* ... */ },
                cardOnClick = { /* ... */ }
            )
            Log.e("FoodImage",food.foodImageName)
        }
    }
}

