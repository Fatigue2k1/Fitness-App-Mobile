package com.example.fitnessapp.screen.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.fitnessapp.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val viewModel: LoginViewModel = viewModel()
    val loginSuccessful by viewModel.loginSuccessful.observeAsState(false)
    val loginError by viewModel.loginError.observeAsState("")

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
                    .clickable { navController.navigate("signup") }
            )
            Text(
                text = "Sign In",
                color = Color.White,
                modifier = Modifier
                    .clickable { /* Navigate to Sign In screen */ }
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
                text = "WELCOME BACK,",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "ENTER YOUR CREDENTIALS BELOW OR SIGN UP FOR A NEW ACCOUNT",
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
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            scope.launch {
                                viewModel.login(email.value, password.value)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Yellow)
                    ) {
                        Text("Sign In", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    if (loginSuccessful) {
                        Text("Login successful!", color = Color.White)
                        LaunchedEffect(loginSuccessful) {
                            // Navigate to the main screen or dashboard
                        }
                    }
                    if (loginError.isNotEmpty()) {
                        Text(loginError, color = Color.White)
                    }
                }
            }
        }
    }
}
