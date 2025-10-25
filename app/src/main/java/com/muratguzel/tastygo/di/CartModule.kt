package com.muratguzel.tastygo.di

import com.muratguzel.tastygo.data.remote.CartAPI
import com.muratguzel.tastygo.data.repository.CartRepositoryImpl
import com.muratguzel.tastygo.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Provides @Singleton
    fun provideCartApi(retrofit: Retrofit): CartAPI =
        retrofit.create(CartAPI::class.java)

    @Provides @Singleton
    fun provideCartRepository(cartAPI: CartAPI): CartRepository =
        CartRepositoryImpl(cartAPI)
}