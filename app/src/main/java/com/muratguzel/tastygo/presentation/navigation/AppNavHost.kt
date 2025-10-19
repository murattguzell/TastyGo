package com.muratguzel.tastygo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.muratguzel.tastygo.presentation.foods.view.FoodListScreen
import com.muratguzel.tastygo.presentation.onboarding.view.OnBoardingScreen
import com.muratguzel.tastygo.presentation.splash.view.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route) {
            SplashScreen {
                navController.navigate(NavigationItem.OnBoarding.route) {
                    popUpTo(NavigationItem.Splash.route) { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
        composable(NavigationItem.OnBoarding.route) {
            OnBoardingScreen {
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