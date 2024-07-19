//package com.example.fitnessapp.screen.history_screen
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.fitnessapp.database.WorkoutHistory
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class HistoryViewModel : ViewModel() {
//    private val _history = MutableStateFlow<List<WorkoutHistory>>(emptyList())
//    val history: StateFlow<List<WorkoutHistory>> = _history
//
//    init {
//        loadHistory()
//    }
//
//    private fun loadHistory() {
//        viewModelScope.launch {
//            // Simulate loading data from a repository or database
//            _history.value = listOf(
//                WorkoutHistory(
//                    workoutName = "Push-up",
//                    date = "2024-07-19",
//                    notes = "Completed 3 sets of 10 reps"
//                ),
//                WorkoutHistory(
//                    workoutName = "Sit-up",
//                    date = "2024-07-18",
//                    notes = "Completed 3 sets of 15 reps"
//                )
//            )
//        }
//    }
//}
