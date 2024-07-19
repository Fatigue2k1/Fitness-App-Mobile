package com.example.fitnessapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.screen.change_password_screen.ChangePasswordScreen
import com.example.fitnessapp.screen.forgot_password_screen.ForgotPasswordScreen
import com.example.fitnessapp.screen.login_screen.LoginScreen
import com.example.fitnessapp.screen.signup_screen.SignUpScreen
import com.example.fitnessapp.screen.welcome_screen.WelcomeScreen
import com.example.fitnessapp.screen.main_screen.MainScreen

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("signup") { SignUpScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("change_password") { ChangePasswordScreen(navController) }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("main") { MainScreen(navController) } // Ensure this route exists
    }
}

