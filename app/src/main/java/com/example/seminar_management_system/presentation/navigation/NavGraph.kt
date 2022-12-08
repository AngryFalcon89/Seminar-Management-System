package com.example.seminar_management_system.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.seminar_management_system.presentation.screens.entrance.signin.screen.SignupPage
import com.example.seminar_management_system.presentation.screens.entrance.splash.screen.SplashScreen
import com.example.seminar_management_system.presentation.screens.login.LoginPage
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.screen.HomeScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    )
    {
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginPage(navController = navController)
        }
        composable(route = Screen.Signup.route){
            SignupPage(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
    }
}