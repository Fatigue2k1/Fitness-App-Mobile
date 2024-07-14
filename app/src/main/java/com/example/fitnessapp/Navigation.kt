package com.example.fitnessapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.screen.LoginScreen
import com.example.fitnessapp.screen.SignUpScreen
import com.example.fitnessapp.screen.WelcomeScreen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("login") { LoginScreen(navController) }
//        composable("newpassword") { NewPasswordScreen(navController) }
//        composable("changepassword") { ChangePasswordScreen(navController) }
    }
}
