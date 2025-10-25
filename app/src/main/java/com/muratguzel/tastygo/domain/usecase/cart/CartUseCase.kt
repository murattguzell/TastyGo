package com.muratguzel.tastygo.domain.usecase.cart

import android.util.Log
import com.muratguzel.tastygo.domain.model.cart.AddToCartRequest
import com.muratguzel.tastygo.domain.model.cart.AddToCartResult
import com.muratguzel.tastygo.domain.model.cart.Cart
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartRequest
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartResult
import com.muratguzel.tastygo.domain.model.cart.GetCartRequest
import com.muratguzel.tastygo.domain.model.cart.GetCartResult
import com.muratguzel.tastygo.domain.repository.CartRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import okio.IOException
import com.muratguzel.tastygo.util.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ConcurrentHashMap

class CartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    private val addLocks = ConcurrentHashMap<String, Mutex>()
    private fun keyOf(user: String, food: String, img: String) = "$user|$food|$img"
    private fun lockFor(user: String, food: String, img: String) =
        addLocks.getOrPut(keyOf(user, food, img)) { Mutex() }

    fun addOrIncrease(request: AddToCartRequest): Flow<Resource<AddToCartResult>> = flow {
        emit(Resource.Loading())

        val lock = lockFor(request.userName, request.foodName, request.foodImageName)
        val result = lock.withLock {
            // ↓↓↓ Kritik bölüm: bu blok aynı ürün için asla paralel koşmaz
            val current = try {
                cartRepository.getCart(GetCartRequest(request.userName))
            } catch (_: Exception) {
                GetCartResult(cartItems = emptyList(), success = false)
            }

            val existing = current.cartItems.firstOrNull {
                it.foodName.equals(request.foodName, ignoreCase = true) &&
                        it.foodImageName == request.foodImageName
            }

            val newQty = (existing?.orderQuantity?.toIntOrNull() ?: 0) +
                    (request.orderQuantity.toIntOrNull() ?: 1)

            if (existing != null) {
                cartRepository.deleteCart(
                    DeleteFromCartRequest(
                        userName = request.userName,
                        cartItemId = existing.cartItemId
                    )
                )
            }

            cartRepository.addCart(request.copy(orderQuantity = newQty.toString()))
        }

        if (result.success) emit(Resource.Success(result))
        else emit(Resource.Error(result.message ?: "Add/increase failed"))
    }.catch { e ->
        emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
    }

    /**
     * Sepet öğelerini getirir. UI tarafı Resource'a göre loading/success/error durumlarını yönetir.
     */
    fun getCart(request: GetCartRequest): Flow<Resource<List<Cart>>> = flow {
        try {
            emit(Resource.Loading())
            val result = cartRepository.getCart(request)

           result.cartItems.forEach {
               Log.e("Cart_ITEM",  "name=${it.foodName}, price=${it.foodPrice}, qty=${it.orderQuantity}, image=${it.foodImageName}")
           }

            if (result.success) {
                emit(Resource.Success(result.cartItems))
            } else {
                emit(Resource.Error("No data!"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HTTP error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

    /**
     * Sepetten ürün siler.
     */
    fun deleteFromCart(request: DeleteFromCartRequest): Flow<Resource<DeleteFromCartResult>> = flow {
        try {
            emit(Resource.Loading())
            val result = cartRepository.deleteCart(request)
            if (result.success) {
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error(result.message ?: "Delete from cart failed"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HTTP error"))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unknown error"))
        }
    }

}