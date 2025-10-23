package com.muratguzel.tastygo.domain.usecase.food

import android.util.Log
import com.muratguzel.tastygo.data.mapper.toFood
import com.muratguzel.tastygo.domain.model.Food
import com.muratguzel.tastygo.domain.repository.FoodRepository
import com.muratguzel.tastygo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class FoodUseCase @Inject constructor(private val foodRepository: FoodRepository) {

    fun getFood(): Flow<Resource<List<Food>>> = flow {
        try {
            emit(Resource.Loading())
            val food = foodRepository.getFood()

            if (food.success == 1) {
                emit(Resource.Success(food.toFood()))

            } else {
                emit(Resource.Error("No data!"))
            }

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error!"))
            Log.e("hata", e.localizedMessage ?: "Error!")
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Error!"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Error!"))
        }
    }
}