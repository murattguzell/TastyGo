package com.muratguzel.tastygo.domain.repository

import com.muratguzel.tastygo.data.local.entity.FavoriteFoodEntity
import com.muratguzel.tastygo.domain.model.food.Food
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun favorites(): Flow<List<FavoriteFoodEntity>>
    fun favoriteIds(): Flow<Set<String>>
    suspend fun toggleFavorite(food: Food)
}