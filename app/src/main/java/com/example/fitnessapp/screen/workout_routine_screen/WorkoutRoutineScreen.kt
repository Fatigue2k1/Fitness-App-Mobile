package com.example.fitnessapp.screen.workout_routine_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fitnessapp.database.Workout

@Composable
fun WorkoutRoutineScreen(navController: NavHostController) {
    val viewModel: WorkoutRoutineViewModel = viewModel()
    val workouts by viewModel.workouts.observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var selectedWorkout by remember { mutableStateOf<Workout?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Workout Routines", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(workouts) { workout ->
                WorkoutItem(workout,
                    onDelete = {
                        viewModel.deleteWorkout(workout)
                    },
                    onClick = {
                        selectedWorkout = workout
                        showDialog = true
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = { showDialog = true },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Workout")
            }
        }
    }

    if (showDialog) {
        selectedWorkout?.let { workout ->
            WorkoutDialog(
                workout = workout,
                onDismiss = { showDialog = false },
                onFinish = {
                    viewModel.addToHistory(workout.name)
                    showDialog = false
                }
            )
        } ?: AddWorkoutDialog(
            onDismiss = { showDialog = false },
            onAdd = { name, description ->
                viewModel.addWorkout(name, description)
                showDialog = false
            }
        )
    }
}

@Composable
fun WorkoutItem(workout: Workout, onDelete: () -> Unit, onClick: () -> Unit) {
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
            Text(text = workout.name, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Workout")
                }
            }
        }
    }
}

@Composable
fun AddWorkoutDialog(onDismiss: () -> Unit, onAdd: (String, String) -> Unit) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Workout") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Workout Name") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Workout Description") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onAdd(name, description) }) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun WorkoutDialog(workout: Workout, onDismiss: () -> Unit, onFinish: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(workout.name) },
        text = { Text(workout.description) },
        confirmButton = {
            TextButton(onClick = onFinish) {
                Text("Finish")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
