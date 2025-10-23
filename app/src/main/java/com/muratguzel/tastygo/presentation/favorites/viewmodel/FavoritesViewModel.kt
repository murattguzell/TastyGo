package com.muratguzel.tastygo.presentation.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muratguzel.tastygo.data.mapper.toFood
import com.muratguzel.tastygo.data.repository.FavoriteRepository
import com.muratguzel.tastygo.domain.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    val favorites: StateFlow<List<Food>> =
        favoriteRepository.favorites()
            .map { list -> list.toFood() }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val favoriteIds: StateFlow<Set<String>> =
        favoriteRepository.favoriteIds()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    fun removeOrInsert(id: String) = viewModelScope.launch {
        favoriteRepository.toggleFavorite(food = Food(id))
    }

    fun onFavoriteClick(food: Food) = viewModelScope.launch {
        favoriteRepository.toggleFavorite(food)
    }
}