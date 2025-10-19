package com.muratguzel.tastygo.data.remote.dto

data class FoodDTO(
    val success: Int,
    val yemekler: List<Yemekler>
)