package com.muratguzel.tastygo.di

import com.muratguzel.tastygo.data.remote.CartAPI
import com.muratguzel.tastygo.data.remote.FoodAPI
import com.muratguzel.tastygo.data.repository.FoodRepositoryImpl
import com.muratguzel.tastygo.domain.repository.FoodRepository
import com.muratguzel.tastygo.util.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FoodModule {

    @Singleton
    @Provides
    fun provideFoodApi(retrofit: Retrofit): FoodAPI =
        retrofit.create(FoodAPI::class.java)

    @Singleton
    @Provides
    fun provideFoodRepository(foodAPI: FoodAPI): FoodRepository{
        return FoodRepositoryImpl(foodAPI)
    }
}