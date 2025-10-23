package com.muratguzel.tastygo.data.local.dao

import androidx.room.*
import com.muratguzel.tastygo.data.local.entity.FavoriteFoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_foods ORDER BY title ASC")
    fun getFavorites(): Flow<List<FavoriteFoodEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_foods WHERE id = :id)")
    fun isFavoriteFlow(id: String): Flow<Boolean>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_foods WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(item: FavoriteFoodEntity)

    @Query("DELETE FROM favorite_foods WHERE id = :id")
    suspend fun deleteById(id: String)
}