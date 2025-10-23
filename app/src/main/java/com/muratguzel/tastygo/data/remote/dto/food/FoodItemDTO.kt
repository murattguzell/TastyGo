package com.muratguzel.tastygo.data.remote.dto.food

import com.google.gson.annotations.SerializedName

data class FoodItemDTO(
    @SerializedName("yemek_adi")
    val foodName: String,

    @SerializedName("yemek_fiyat")
    val foodPrice: String,

    @SerializedName("yemek_id")
    val foodId: String,

    @SerializedName("yemek_resim_adi")
    val foodImageName: String
)
