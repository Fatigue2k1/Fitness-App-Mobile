package com.example.fitnessapp.screen.workout_routine_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import com.example.fitnessapp.database.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorkoutRoutineViewModel(application: Application) : AndroidViewModel(application) {

    private val workoutDao = AppDatabase.getDatabase(application).workoutDao()
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
                    Workout(name = "Chest", description = "Bench press, Incline Press, Dip"),
                    Workout(name = "Shoulder", description = "Shoulder press, Side lateral raise, Front Raise")
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
}