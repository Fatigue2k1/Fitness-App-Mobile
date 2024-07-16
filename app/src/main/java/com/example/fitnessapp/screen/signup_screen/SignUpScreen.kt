package com.example.fitnessapp.screen.signup_screen

import com.example.fitnessapp.screen.signup_screen.SignUpViewModel
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import com.example.fitnessapp.R

@Composable
fun SignUpScreen(navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val viewModel: SignUpViewModel = viewModel()
    val userCreated by viewModel.userCreated.observeAsState(false)
    val userExists by viewModel.userExists.observeAsState(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { /* Navigate to Sign Up screen */ }
            )
            Text(
                text = "Sign In",
                color = Color.White,
                modifier = Modifier
                    .clickable { navController.navigate("login") }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.height(300.dp))

            Text(
                text = "HELLO ROOKIES,",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "ENTER YOUR INFORMATION BELOW OR LOGIN WITH ANOTHER ACCOUNT",
                color = Color.White,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.Center)
                .offset(y = 100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.7f))
                    .padding(16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text("Email", color = Color.White) },
                        textStyle = TextStyle(color = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { password.value = it },
                        label = { Text("Password", color = Color.White) },
                        visualTransformation = PasswordVisualTransformation(),
                        textStyle = TextStyle(color = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = confirmPassword.value,
                        onValueChange = { confirmPassword.value = it },
                        label = { Text("Confirm Password", color = Color.White) },
                        visualTransformation = PasswordVisualTransformation(),
                        textStyle = TextStyle(color = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            if (password.value == confirmPassword.value) {
                                scope.launch {
                                    viewModel.signUp(email.value, password.value)
                                }
                            } else {
                                Log.d("SignUpScreen", "Passwords do not match")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Yellow)
                    ) {
                        Text("Sign Up", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (userCreated) {
                        Text("User created successfully!", color = Color.White)
                        LaunchedEffect(userCreated) {
                            navController.navigate("login")
                        }
                    }
                    if (userExists) {
                        Text("User already exists", color = Color.White)
                    }
                }
            }
        }
    }
}
