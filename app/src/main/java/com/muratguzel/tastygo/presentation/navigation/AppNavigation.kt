package com.muratguzel.tastygo.presentation.navigation

enum class Screen {
    SPLASH,
    ONBOARDING,
    FOODLISTSCREEN
}

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name)
    object OnBoarding : NavigationItem(
        Screen.ONBOARDING.name
    )
    object FoodListScreen : NavigationItem(
        Screen.FOODLISTSCREEN.name
    )
}