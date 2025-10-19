package com.muratguzel.tastygo.data.mapper

import com.muratguzel.tastygo.data.remote.dto.FoodDTO
import com.muratguzel.tastygo.domain.model.Food
import com.muratguzel.tastygo.util.Constant.BASE_IMAGE_URL

fun FoodDTO.toFood(): List<Food> {
    return yemekler.map {yemek ->
        Food(
            foodId = yemek.yemek_adi,
            foodName = yemek.yemek_adi,
            foodPrice = yemek.yemek_fiyat,
            foodImageName = BASE_IMAGE_URL + yemek.yemek_resim_adi
        )
    }
}

