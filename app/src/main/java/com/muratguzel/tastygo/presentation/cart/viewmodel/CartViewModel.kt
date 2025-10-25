package com.muratguzel.tastygo.presentation.cart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratguzel.tastygo.domain.model.cart.AddToCartRequest
import com.muratguzel.tastygo.domain.model.cart.AddToCartResult
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartRequest
import com.muratguzel.tastygo.domain.model.cart.DeleteFromCartResult
import com.muratguzel.tastygo.domain.model.cart.GetCartRequest
import com.muratguzel.tastygo.domain.usecase.cart.CartUseCase
import com.muratguzel.tastygo.presentation.cart.state.CartState
import com.muratguzel.tastygo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    private val currentUserName = MutableStateFlow<String?>(null)

    // Manual refresh trigger for reloading cart after add/delete or tab re-entry
    private val refresh = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    // ViewState computed reactively via stateIn.
    // It refreshes on: (a) userName set via loadCart(), (b) any emit on 'refresh'
    val state: StateFlow<CartState> =
        combine(
            currentUserName.filterNotNull(),
            refresh.onStart { emit(Unit) } // initial load
        ) { name, _ -> name }
            .flatMapLatest { name ->
                cartUseCase.getCart(GetCartRequest(name))
            }
            .map { res ->
                when (res) {
                    is Resource.Loading -> CartState(isLoading = true, food = emptyList(), error = "")
                    is Resource.Success -> CartState(isLoading = false, food = res.data ?: emptyList(), error = "")
                    is Resource.Error -> CartState(isLoading = false, food = emptyList(), error = res.message ?: "Bilinmeyen hata")
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = CartState()
            )

    /**
     * Sepeti yükler.
     */
    fun loadCart(userName: String) {
        currentUserName.value = userName
        // trigger initial/explicit refresh when tab is opened
        refresh.tryEmit(Unit)
    }

    /**
     * Sepetten ürün silme akışı (StateFlow).
     */
    fun deleteFromCart(request: DeleteFromCartRequest): StateFlow<Resource<DeleteFromCartResult>> =
        cartUseCase.deleteFromCart(request)
            .onEach { res ->
                if (res is Resource.Success && res.data?.success == true) {
                    refresh.tryEmit(Unit)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = Resource.Loading()
            )

    fun addToCartSmart(request: AddToCartRequest): StateFlow<Resource<AddToCartResult>> =
        cartUseCase.addOrIncrease(request)
            .onEach { res ->
                if (res is Resource.Success && res.data?.success == true) {
                    // Listeyi yenile
                    refresh.tryEmit(Unit)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = Resource.Loading()
            )
}

