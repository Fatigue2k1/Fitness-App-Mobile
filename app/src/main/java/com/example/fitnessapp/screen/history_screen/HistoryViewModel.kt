package com.example.fitnessapp.screen.history_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import com.example.fitnessapp.database.WorkoutHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val workoutHistoryDao = AppDatabase.getDatabase(application).workoutHistoryDao()
    private val _workoutHistory = MutableLiveData<List<WorkoutHistory>>()
    val workoutHistory: LiveData<List<WorkoutHistory>> get() = _workoutHistory

    init {
        fetchWorkoutHistory()
    }

    private fun fetchWorkoutHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val history = workoutHistoryDao.getAllWorkoutHistory()
            _workoutHistory.postValue(history)
        }
    }

    fun addToHistory(workoutName: String, details: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val history = WorkoutHistory(workoutName = workoutName, timestamp = System.currentTimeMillis(), details = details)
            workoutHistoryDao.insertWorkoutHistory(history)
        }
    }
}