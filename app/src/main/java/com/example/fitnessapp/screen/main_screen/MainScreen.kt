package com.example.fitnessapp.screen.main_screen

import android.annotation.SuppressLint
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitnessapp.R
import com.example.fitnessapp.screen.exercises_screen.ExercisesScreen
import com.example.fitnessapp.screen.history_screen.HistoryScreen
import com.example.fitnessapp.screen.workout_routine_screen.WorkoutRoutineScreen

sealed class MainScreen(val route: String, val icon: Int, val label: String) {
    object Workout : MainScreen("workout", R.drawable.ic_workout, "Workouts")
    object Exercises : MainScreen("exercises", R.drawable.ic_exercises, "Exercises")
    object History : MainScreen("history", R.drawable.ic_history, "History")
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val screens = listOf(MainScreen.Workout, MainScreen.Exercises, MainScreen.History)
    var currentScreen by remember { mutableStateOf<MainScreen>(MainScreen.Workout) }

    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color.Gray
            ) {
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(id = screen.icon), contentDescription = null) },
                        label = { Text(screen.label) },
                        selected = currentScreen == screen,
                        onClick = {
                            currentScreen = screen
                            navHostController.navigate(screen.route) {
                                popUpTo(navHostController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        selectedContentColor = Color.Blue,
                        unselectedContentColor = Color.Gray
                    )
                }
            }
        }
    ) {
        NavHost(navController = navHostController, startDestination = MainScreen.Workout.route) {
            composable(MainScreen.Workout.route) { WorkoutRoutineScreen(navHostController) }
            composable(MainScreen.Exercises.route) { ExercisesScreen() }
            composable(MainScreen.History.route) { HistoryScreen() }
        }
    }
}
