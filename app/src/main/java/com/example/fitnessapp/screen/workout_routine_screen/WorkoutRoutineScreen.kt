package com.example.fitnessapp.screen.workout_routine_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var workoutDetails by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "Workout Routines", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(workouts) { workout ->
                WorkoutItem(
                    workout = workout,
                    onDelete = { viewModel.deleteWorkout(workout) },
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
                onClick = { showDialog = true }, // Open AddWorkoutDialog
                modifier = Modifier.padding(60.dp)
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
                    viewModel.addToHistory(workout.name, workoutDetails)
                    showDialog = false
                },
                onDetailsChange = { details -> workoutDetails = details }
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
            .clickable { onClick() }
            .width(300.dp) // Adjust width as needed
            .height(65.dp), // Adjust height as needed
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
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
            TextButton(onClick = {
                if (name.isNotBlank() && description.isNotBlank()) {
                    onAdd(name, description)
                }
            }) {
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
fun WorkoutDialog(
    workout: Workout,
    onDismiss: () -> Unit,
    onFinish: () -> Unit,
    onDetailsChange: (String) -> Unit
) {
    var details by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(workout.name) },
        text = {
            Column {
                Text(text = workout.description)
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = details,
                    onValueChange = { newDetails ->
                        details = newDetails
                        onDetailsChange(newDetails)
                    },
                    label = { Text("Details") }
                )
            }
        },
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
