package com.muratguzel.tastygo.data.repository

import com.muratguzel.tastygo.data.local.dao.FavoriteDao
import com.muratguzel.tastygo.data.local.entity.FavoriteFoodEntity
import com.muratguzel.tastygo.domain.model.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: FavoriteDao
) {
    fun favorites(): Flow<List<FavoriteFoodEntity>> = dao.getFavorites()

    fun favoriteIds(): Flow<Set<String>> =
        dao.getFavorites().map { list -> list.map { it.id }.toSet() }

    suspend fun toggleFavorite(
     food: Food
    ) {
        if (dao.isFavorite(food.foodId)) dao.deleteById(food.foodId)
        else dao.insertFavorite(FavoriteFoodEntity(food.foodId, food.foodName, food.foodPrice, food.foodImageName))
    }
}