package com.example.fitnessapp.screen.workout_routine_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import com.example.fitnessapp.database.Workout
import com.example.fitnessapp.database.WorkoutHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutRoutineViewModel(application: Application) : AndroidViewModel(application) {

    private val workoutDao = AppDatabase.getDatabase(application).workoutDao()
    private val workoutHistoryDao = AppDatabase.getDatabase(application).workoutHistoryDao()
    private val _workouts = MutableLiveData<List<Workout>>()
    val workouts: LiveData<List<Workout>> get() = _workouts

    init {
        fetchWorkouts()
    }

    private fun fetchWorkouts() {
        viewModelScope.launch(Dispatchers.IO) {
            val currentWorkouts = workoutDao.getAllWorkouts()
            if (currentWorkouts.isEmpty()) {
                val predefinedWorkouts = listOf(
                    Workout(name = "Chest", description = "Bench press, Incline Bench Press, Bar Dips"),
                    Workout(name = "Shoulder", description = "Overhead press, Dumbbell lateral raise, Dumbbell Shoulder Press"),
                    Workout(name = "Biceps", description = "Barbell Curl, Incline Dumbbell Curl, Chin-Up"),
                    Workout(name = "Triceps", description = "Barbell Standing Triceps Extension, Tricep Push Down With Bar, Tricep Push Down With Rope"),
                    Workout(name = "Back", description = "Barbell Rows, Pull-Ups, Romanian Deadlift"),
                    Workout(name = "Legs", description = "Hanging Leg Raise, Lying Leg Raise, Leg Extension, Leg Press"),
                    Workout(name = "Abs", description = "Kneeling Abs Wheel Roll-Out, Crunches, Plank")
                )
                predefinedWorkouts.forEach { workoutDao.insertWorkout(it) }
                _workouts.postValue(predefinedWorkouts)
            } else {
                _workouts.postValue(currentWorkouts)
            }
        }
    }

    fun addWorkout(name: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val workout = Workout(name = name, description = description)
            workoutDao.insertWorkout(workout)
            _workouts.postValue(workoutDao.getAllWorkouts())
        }
    }

    fun deleteWorkout(workout: Workout) {
        viewModelScope.launch(Dispatchers.IO) {
            workoutDao.deleteWorkout(workout)
            _workouts.postValue(workoutDao.getAllWorkouts())
        }
    }

    fun addToHistory(workoutName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val history = WorkoutHistory(workoutName = workoutName, timestamp = System.currentTimeMillis())
            workoutHistoryDao.insertWorkoutHistory(history)
        }
    }
}
