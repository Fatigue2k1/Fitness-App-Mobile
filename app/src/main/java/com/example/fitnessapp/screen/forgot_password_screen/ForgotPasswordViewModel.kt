package com.example.fitnessapp.screen.forgot_password_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val _passwordReset = MutableLiveData<Boolean>()
    val passwordReset: LiveData<Boolean> get() = _passwordReset

    private val _resetPasswordError = MutableLiveData<String>()
    val resetPasswordError: LiveData<String> get() = _resetPasswordError

    fun resetPassword(email: String, newPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = userDao.getUserByEmail(email)
                if (user != null) {
                    userDao.updatePassword(email, newPassword)
                    _passwordReset.postValue(true)
                } else {
                    _resetPasswordError.postValue("User not found")
                }
            } catch (e: Exception) {
                _resetPasswordError.postValue("An error occurred")
            }
        }
    }
}