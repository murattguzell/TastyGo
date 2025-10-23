package com.muratguzel.tastygo.data.remote.dto.food

import com.google.gson.annotations.SerializedName

data class FoodListResponseDTO(
    val success: Int,
    @SerializedName("yemekler")
    val foods: List<FoodItemDTO>
)