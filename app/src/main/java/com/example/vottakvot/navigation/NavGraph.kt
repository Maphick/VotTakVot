package com.example.vottakvot.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.screen.HomeScreen
import com.example.vottakvot.screen.InquirerScreen
import com.example.vottakvot.screen.WelcomeScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
    welcomeViewModel: WelcomeViewModel,
    inquirerViewModel: InquirerViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController, welcomeViewModel = welcomeViewModel)
        }
        composable(route = Screen.Inquirer.route) {
            InquirerScreen(navController = navController, inquirerViewModel = inquirerViewModel)
        }
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
    }
}