package com.muratguzel.tastygo.data.remote.dto.cart

import com.google.gson.annotations.SerializedName

data class GetCartItemsRequestDTO(
    @SerializedName("kullanici_adi")
    val userName: String)