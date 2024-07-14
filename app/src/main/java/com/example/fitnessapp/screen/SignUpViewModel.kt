package com.example.fitnessapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.API
import com.example.fitnessapp.SignUpRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel : ViewModel() {
    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("SignUpViewModel", "signUp: Making network request")
            try {
                val response = API.service.signUp(SignUpRequest(email, password))
                withContext(Dispatchers.Main) {
                    if (response.success) {
                        Log.d("SignUpViewModel", "signUp: Successful sign-up")
                        // Handle successful sign-up (e.g., navigate to the login screen)
                    } else {
                        Log.d("SignUpViewModel", "signUp: Unsuccessful sign-up")
                        // Handle unsuccessful sign-up (e.g., show error message)
                    }
                }
            } catch (e: Exception) {
                Log.e("SignUpViewModel", "signUp: Error occurred", e)
                // Handle network or other errors
            }
        }
    }
}
