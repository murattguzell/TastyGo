package com.muratguzel.tastygo.data.repository

import com.muratguzel.tastygo.data.remote.FoodAPI
import com.muratguzel.tastygo.data.remote.dto.FoodDTO
import com.muratguzel.tastygo.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodAPI: FoodAPI
) : FoodRepository  {
    override suspend fun getFood(): FoodDTO {
        return foodAPI.getFood()
    }
}