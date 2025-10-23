package com.muratguzel.tastygo.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    val foodId: String = "",
    val foodName: String = "",
    val foodImageName: String = "",
    val foodPrice: String = ""
) : Parcelable