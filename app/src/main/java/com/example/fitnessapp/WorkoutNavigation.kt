//package com.example.fitnessapp
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.Icon
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.fitnessapp.screen.exercises_screen.ExercisesScreen
//import com.example.fitnessapp.screen.history_screen.HistoryScreen
//import com.example.fitnessapp.screen.workout_routine_screen.WorkoutRoutineScreen
//
//sealed class WorkoutScreen(val route: String, val icon: Int, val label: String) {
//    object Workout : WorkoutScreen("workout", R.drawable.ic_workout, "Workouts")
//    object Exercises : WorkoutScreen("exercises", R.drawable.ic_exercises, "Exercises")
//    object History : WorkoutScreen("history", R.drawable.ic_history, "History")
//}
//
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun MainScreen() {
//    val navController = rememberNavController()
//    val screens = listOf(WorkoutScreen.Workout, WorkoutScreen.Exercises, WorkoutScreen.History)
//    val currentScreen = remember { mutableStateOf<WorkoutScreen>(WorkoutScreen.Workout) }
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigation(
//                backgroundColor = Color.White,
//                contentColor = Color.Gray
//            ) {
//                screens.forEach { screen ->
//                    BottomNavigationItem(
//                        icon = { Icon(painterResource(id = screen.icon), contentDescription = null) },
//                        label = { Text(screen.label) },
//                        selected = currentScreen.value == screen,
//                        onClick = {
//                            currentScreen.value = screen
//                            navController.navigate(screen.route) {
//                                popUpTo(navController.graph.startDestinationId) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        },
//                        selectedContentColor = Color.Blue,
//                        unselectedContentColor = Color.Gray
//                    )
//                }
//            }
//        }
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            NavHost(navController = navController, startDestination = WorkoutScreen.Workout.route) {
//                composable(WorkoutScreen.Workout.route) { WorkoutRoutineScreen(navController) }
//                composable(WorkoutScreen.Exercises.route) { ExercisesScreen(navController) }
//                composable(WorkoutScreen.History.route) { HistoryScreen(navController) }
//            }
//        }
//    }
//}
