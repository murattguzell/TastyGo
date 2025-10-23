package com.muratguzel.tastygo.data.repository

import com.muratguzel.tastygo.data.remote.FoodAPI
import com.muratguzel.tastygo.data.remote.dto.food.FoodListResponseDTO
import com.muratguzel.tastygo.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodAPI: FoodAPI
) : FoodRepository  {
    override suspend fun getFood(): FoodListResponseDTO {
        return foodAPI.getFood()
    }
}