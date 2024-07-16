package com.example.fitnessapp.screen.forgot_password_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ForgotPasswordScreen(navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val viewModel: ForgotPasswordViewModel = viewModel()
    val passwordReset by viewModel.passwordReset.observeAsState(false)
    val resetPasswordError by viewModel.resetPasswordError.observeAsState("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Forgot Password",
            color = Color.Black,
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.resetPassword(email.value)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Password")
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (passwordReset) {
            Text("Password reset instructions sent to your email!")
        }
        if (resetPasswordError.isNotEmpty()) {
            Text(resetPasswordError, color = Color.Red)
        }
    }
}
