package com.muratguzel.tastygo.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muratguzel.tastygo.domain.model.food.Food
import com.muratguzel.tastygo.presentation.cart.view.CartScreen
import com.muratguzel.tastygo.presentation.detail.view.FoodDetailScreen
import com.muratguzel.tastygo.presentation.favorites.view.FavoritesScreen
import com.muratguzel.tastygo.presentation.foods.view.FoodListScreen
import com.muratguzel.tastygo.presentation.onboarding.view.OnBoardingScreen
import com.muratguzel.tastygo.presentation.onboarding.viewmodel.OnboardingViewModel
import com.muratguzel.tastygo.presentation.splash.view.SplashScreen
import com.muratguzel.tastygo.presentation.navigation.components.BottomBar
import com.muratguzel.tastygo.presentation.profile.view.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
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
                    "main_nav"
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
                navController.navigate("main_nav") {
                    launchSingleTop = true
                }
            }
        }

        composable(route = NavigationItem.FoodDetailScreen.route) {
            val food: Food? = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get("food")

            if (food != null) {
                FoodDetailScreen(food = food, onBackClick = {navController.popBackStack()})
            }
        }


        composable("main_nav") {
            val bottomNavController = rememberNavController()
            Scaffold(
                bottomBar = { BottomBar(navController = bottomNavController) },
                containerColor = Color.Transparent,
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text("TastyGO", color = Color.Black) },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    )
                },

            ) { innerPadding ->
                NavHost(
                    navController = bottomNavController,
                    startDestination = Screen.FOODLISTSCREEN.name,
                    modifier = Modifier.padding(innerPadding),
                ) {
                    //
                    composable(Screen.FOODLISTSCREEN.name) {
                        FoodListScreen(
                            cardOnClick = { food ->
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("food", food)

                                navController.navigate(NavigationItem.FoodDetailScreen.route)
                            },


                        )
                    }
                    //
                    composable(Screen.FAVORITES.name) { FavoritesScreen(cardOnClick = { food ->
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("food", food)

                        navController.navigate(NavigationItem.FoodDetailScreen.route)
                    }) }
                    composable(Screen.CART.name) { CartScreen() }
                    composable(Screen.PROFILE.name) { ProfileScreen() }
                }
            }
        }
    }
}