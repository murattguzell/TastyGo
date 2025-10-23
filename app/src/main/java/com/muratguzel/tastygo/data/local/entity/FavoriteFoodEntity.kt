package com.muratguzel.tastygo.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_foods")
data class FavoriteFoodEntity(
    @PrimaryKey val id: String,
    val title: String,
    val price: String,
    val imageUrl: String
)