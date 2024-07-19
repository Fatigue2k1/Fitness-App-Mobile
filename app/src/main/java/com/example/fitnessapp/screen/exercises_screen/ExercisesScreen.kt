package com.example.fitnessapp.screen.exercises_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

data class Exercise(
    val name: String,
    val guideline: String,
    val description: String,
    val targetMuscle: String
)

// Sample data
val exercises = listOf(
    Exercise(
        name = "Bar Dips",
        guideline = "Grip a dip station about shoulder-width apart, and climb or jump to get into the starting position. " +
                "Lower yourself with control until your shoulder is below your elbow, or as deep as you comfortably can. " +
                "Reverse the motion and return to the starting position.",
        description = "The bar dip is a classic upper-body exercise, and for most people, heavy enough to give a good training effect with the body weight alone as resistance.",
        targetMuscle = "Primary muscles worked:\nChest\nFront Deltoid\nSecondary muscles worked:\nTriceps"
    )
    // Add more exercises here
)

@Composable
fun ExercisesScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredExercises = remember(searchQuery) {
        exercises.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Exercises") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(filteredExercises) { exercise ->
                ExerciseItem(exercise)
            }
        }
    }
}

@Composable
fun ExerciseItem(exercise: Exercise) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { showDialog = true },
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = exercise.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = "Target Muscle: ${exercise.targetMuscle}", style = MaterialTheme.typography.bodyMedium)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(exercise.name) },
            text = {
                Column {
                    Text(text = "Guideline:", fontWeight = FontWeight.Bold)
                    Text(text = exercise.guideline)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Description:", fontWeight = FontWeight.Bold)
                    Text(text = exercise.description)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Target Muscle:", fontWeight = FontWeight.Bold)
                    Text(text = exercise.targetMuscle)
                }
            },
            confirmButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}
