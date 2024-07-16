package com.example.fitnessapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.fitnessapp.screen.change_password_screen.ChangePasswordScreen
//import com.example.fitnessapp.screen.change_password_screen.ChangePasswordViewModel
//import com.example.fitnessapp.screen.new_password_screen.NewPasswordScreen
//import com.example.fitnessapp.screen.new_password_screen.NewPasswordViewModel
import com.example.fitnessapp.screen.login_screen.LoginScreen
import com.example.fitnessapp.screen.signup_screen.SignUpScreen
import com.example.fitnessapp.screen.welcome_screen.WelcomeScreen

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
