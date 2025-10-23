package com.muratguzel.tastygo.domain.repository

import com.muratguzel.tastygo.data.remote.dto.food.FoodListResponseDTO

interface FoodRepository {
    suspend fun getFood(): FoodListResponseDTO
}