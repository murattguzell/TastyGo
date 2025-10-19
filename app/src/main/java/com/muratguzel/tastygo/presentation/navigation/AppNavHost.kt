package com.muratguzel.tastygo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.muratguzel.tastygo.presentation.foods.view.FoodListScreen
import com.muratguzel.tastygo.presentation.onboarding.view.OnBoardingScreen
import com.muratguzel.tastygo.presentation.onboarding.viewmodel.OnboardingViewModel
import com.muratguzel.tastygo.presentation.splash.view.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route
) {
    val onboardingVm: OnboardingViewModel = hiltViewModel()
    val skipOnboarding by onboardingVm.shouldSkipOnboarding.collectAsState()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen {
                val target = if (skipOnboarding) {
                    NavigationItem.FoodListScreen.route
                } else {
                    NavigationItem.OnBoarding.route
                }
                navController.navigate(target) {
                    popUpTo(NavigationItem.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
        composable(NavigationItem.OnBoarding.route) {
            OnBoardingScreen {
                onboardingVm.completeOnboarding()
                navController.popBackStack(
                    route = NavigationItem.OnBoarding.route,
                    inclusive = true
                )
                navController.navigate(NavigationItem.FoodListScreen.route) {
                    launchSingleTop = true
                }
            }
        }
        composable(NavigationItem.FoodListScreen.route) {
            FoodListScreen()
        }
    }
}