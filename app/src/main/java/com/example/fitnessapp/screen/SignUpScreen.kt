package com.example.fitnessapp.screen

import SignUpViewModel
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val viewModel: SignUpViewModel = viewModel()
    val userCreated by viewModel.userCreated.observeAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text("Confirm Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (password.value == confirmPassword.value) {
                scope.launch {
                    viewModel.signUp(email.value, password.value)
                }
            } else {
                // Display error message to the user
                // e.g., Snackbar, Toast, or an error text field
                Log.d("SignUpScreen", "Passwords do not match")
            }
        }) {
            Text("Sign Up")
        }
        if (userCreated) {
            Text("User created successfully!")
            Button(onClick = {
                navController.navigate("login")
            }) {
                Text("Sign In")
            }
        }
    }
}