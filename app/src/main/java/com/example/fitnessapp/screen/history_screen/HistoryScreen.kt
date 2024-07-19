package com.example.fitnessapp.screen.history_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fitnessapp.database.WorkoutHistory
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(navController: NavHostController) {
    val viewModel: HistoryViewModel = viewModel()
    val workoutHistory by viewModel.workoutHistory.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var selectedHistory by remember { mutableStateOf<WorkoutHistory?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Workout History", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(workoutHistory) { history ->
                HistoryItem(
                    workoutHistory = history,
                    onClick = {
                        selectedHistory = history
                        showDialog = true
                    }
                )
            }
        }
    }

    if (showDialog) {
        selectedHistory?.let { history ->
            HistoryDialog(
                workoutHistory = history,
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Composable
fun HistoryItem(workoutHistory: WorkoutHistory, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = workoutHistory.workoutName, style = MaterialTheme.typography.titleMedium)
            val date = Date(workoutHistory.timestamp)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            Text(text = format.format(date), style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun HistoryDialog(workoutHistory: WorkoutHistory, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(workoutHistory.workoutName) },
        text = {
            Column {
                Text("Exercises completed:")
                // Display the exercises or any other details here
                Text(workoutHistory.details)
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}