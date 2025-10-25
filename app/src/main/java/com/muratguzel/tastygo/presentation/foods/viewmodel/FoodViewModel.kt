package com.muratguzel.tastygo.presentation.foods.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratguzel.tastygo.domain.repository.FavoriteRepository
import com.muratguzel.tastygo.domain.usecase.food.FoodUseCase
import com.muratguzel.tastygo.presentation.foods.state.FoodState
import com.muratguzel.tastygo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val foodUseCase: FoodUseCase,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private val _foodState = mutableStateOf(FoodState())
    val foodState: State<FoodState> = _foodState

    // Tüm favori id'lerini tek set olarak tut (UI için ideal)
    val favoriteIds: StateFlow<Set<String>> =
        favoriteRepository.favoriteIds()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())
    private var job: Job? = null

    init {
        getFood()
    }

    private fun getFood() {
        job?.cancel()
        job = foodUseCase.getFood().onEach {
            when (it) {
                is Resource.Error<*> -> {
                    _foodState.value = foodState.value.copy(
                        error = it.message ?: "error",
                        isLoading = false
                    )
                }

                is Resource.Success -> {
                    _foodState.value = foodState.value.copy(
                        food = it.data ?: emptyList(),
                        isLoading = false
                    )

                }

                is Resource.Loading -> {
                    _foodState.value = foodState.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }


}
