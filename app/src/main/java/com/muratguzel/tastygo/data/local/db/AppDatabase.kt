package com.muratguzel.tastygo.data.local.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.muratguzel.tastygo.data.local.dao.FavoriteDao
import com.muratguzel.tastygo.data.local.entity.FavoriteFoodEntity

@Database(
    entities = [FavoriteFoodEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}