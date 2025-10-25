package com.muratguzel.tastygo.data.mapper

import com.muratguzel.tastygo.data.local.entity.FavoriteFoodEntity
import com.muratguzel.tastygo.data.remote.dto.food.FoodListResponseDTO
import com.muratguzel.tastygo.domain.model.food.Food
import com.muratguzel.tastygo.util.Constant.BASE_IMAGE_URL

fun FoodListResponseDTO.toFood(): List<Food> {
    return foods.map {yemek ->
        Food(
            foodId = yemek.foodId,
            foodName = yemek.foodName,
            foodPrice = yemek.foodPrice,
            foodImageName = BASE_IMAGE_URL + yemek.foodImageName
        )
    }
}

fun List<FavoriteFoodEntity>.toFood(): List<Food> {
    return this.map {entity ->
        Food(
            foodId = entity.id,
            foodName = entity.title,
            foodPrice = entity.price,
            foodImageName = entity.imageUrl
        )
    }
}