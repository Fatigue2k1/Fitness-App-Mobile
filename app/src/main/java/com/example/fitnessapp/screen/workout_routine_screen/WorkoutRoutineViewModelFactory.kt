//package com.example.fitnessapp.screen.workout_routine_screen
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//
//class WorkoutRoutineViewModelFactory(
//    private val application: Application,
//    private val userEmail: String
//) : ViewModelProvider.AndroidViewModelFactory(application) {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(WorkoutRoutineViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return WorkoutRoutineViewModel(application, userEmail) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
