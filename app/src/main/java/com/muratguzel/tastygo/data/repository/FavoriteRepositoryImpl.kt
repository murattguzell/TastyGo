package com.muratguzel.tastygo.data.repository

import com.muratguzel.tastygo.data.local.dao.FavoriteDao
import com.muratguzel.tastygo.data.local.entity.FavoriteFoodEntity
import com.muratguzel.tastygo.domain.model.food.Food
import com.muratguzel.tastygo.domain.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteDao
) : FavoriteRepository{
    override fun favorites(): Flow<List<FavoriteFoodEntity>> = dao.getFavorites()

    override fun favoriteIds(): Flow<Set<String>> =
        dao.getFavorites().map { list -> list.map { it.id }.toSet() }

    override suspend fun toggleFavorite(food: Food) {
        if (dao.isFavorite(food.foodId)) dao.deleteById(food.foodId)
        else dao.insertFavorite(FavoriteFoodEntity(food.foodId, food.foodName, food.foodPrice, food.foodImageName))
    }


}