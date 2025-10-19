package com.muratguzel.tastygo.domain.repository

import com.muratguzel.tastygo.data.remote.dto.FoodDTO

interface FoodRepository {
    suspend fun getFood(): FoodDTO
}