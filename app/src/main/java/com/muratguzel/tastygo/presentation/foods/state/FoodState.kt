package com.muratguzel.tastygo.presentation.foods.state

import com.muratguzel.tastygo.domain.model.Food


data class FoodState(
    val isLoading: Boolean = false,
    val food: List<Food> = emptyList(),
    val error: String = ""
)