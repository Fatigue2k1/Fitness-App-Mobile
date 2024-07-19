//package com.example.fitnessapp.screen.exercises_screen
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.fitnessapp.database.Exercise
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class ExercisesViewModel : ViewModel() {
//    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
//    val exercises: StateFlow<List<Exercise>> = _exercises
//
//    init {
//        loadExercises()
//    }
//
//    private fun loadExercises() {
//        viewModelScope.launch {
//            // Simulate loading data from a repository or database
//            _exercises.value = listOf(
//                Exercise(id = 1, name = "Push-up", description = "A basic push-up exercise"),
//                Exercise(id = 2, name = "Sit-up", description = "A basic sit-up exercise")
//            )
//        }
//    }
//}
