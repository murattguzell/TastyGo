package com.muratguzel.tastygo.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    SPLASH,
    ONBOARDING,
    FOODLISTSCREEN,
    FAVORITES,
    CART,
    PROFILE
}

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object OnBoarding : NavigationItem(Screen.ONBOARDING.name)
    object FoodListScreen : NavigationItem(Screen.FOODLISTSCREEN.name)
}

sealed class BottomNavItem(val route: String, val title: String, val icon: ImageVector) {
    object FoodListScreen : BottomNavItem(Screen.FOODLISTSCREEN.name, "Ana Sayfa", Icons.Filled.Home)
    object Favorites : BottomNavItem(Screen.FAVORITES.name, "Favoriler", Icons.Filled.Favorite)
    object Cart : BottomNavItem(Screen.CART.name, "Sepet", Icons.Filled.ShoppingCart)
    object Profile : BottomNavItem(Screen.PROFILE.name, "Profil", Icons.Filled.Person)
}