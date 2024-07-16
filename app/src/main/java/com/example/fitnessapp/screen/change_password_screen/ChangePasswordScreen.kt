//package com.example.fitnessapp.screen.change_password_screen
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//
//@Composable
//fun ChangePasswordScreen(navController: NavHostController, changePasswordViewModel: ChangePasswordViewModel = viewModel()) {
//    val currentPassword = remember { mutableStateOf("") }
//    val newPassword = remember { mutableStateOf("") }
//    val confirmPassword = remember { mutableStateOf("") }
//    val email = "user@example.com" // Replace with actual user email
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.Black)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//                .align(Alignment.TopCenter)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.Start,
//                verticalAlignment = Alignment.Top
//            ) {
//                Text(
//                    text = "Sign In",
//                    color = Color.White,
//                    modifier = Modifier
//                        .padding(end = 8.dp)
//                        .clickable { navController.navigate("login") }
//                )
//                Text(
//                    text = "Sign Up",
//                    color = Color.White,
//                    modifier = Modifier
//                        .clickable { navController.navigate("signup") }
//                )
//            }
//            Spacer(modifier = Modifier.height(300.dp))
//
//            Text(
//                text = "Change Password",
//                color = Color.White,
//                fontSize = 24.sp,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//            OutlinedTextField(
//                value = currentPassword.value,
//                onValueChange = { currentPassword.value = it },
//                label = { Text("Current Password", color = Color.White) },
//                visualTransformation = PasswordVisualTransformation(),
//                textStyle = TextStyle(color = Color.White),
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(
//                value = newPassword.value,
//                onValueChange = { newPassword.value = it },
//                label = { Text("New Password", color = Color.White) },
//                visualTransformation = PasswordVisualTransformation(),
//                textStyle = TextStyle(color = Color.White),
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            OutlinedTextField(
//                value = confirmPassword.value,
//                onValueChange = { confirmPassword.value = it },
//                label = { Text("Confirm Password", color = Color.White) },
//                visualTransformation = PasswordVisualTransformation(),
//                textStyle = TextStyle(color = Color.White),
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    if (newPassword.value == confirmPassword.value) {
//                        changePasswordViewModel.changePassword(email, currentPassword.value, newPassword.value)
//                    } else {
//                        // Handle password mismatch
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Change Password")
//            }
//        }
//    }
//}
