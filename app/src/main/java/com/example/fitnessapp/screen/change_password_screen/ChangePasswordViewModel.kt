package com.example.fitnessapp.screen.change_password_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    private val _passwordChanged = MutableLiveData<Boolean>()
    val passwordChanged: LiveData<Boolean> get() = _passwordChanged

    private val _changePasswordError = MutableLiveData<String>()
    val changePasswordError: LiveData<String> get() = _changePasswordError

    fun changePassword(email: String, oldPassword: String, newPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = userDao.getUserByEmail(email)
                if (user != null && user.password == oldPassword) {
                    userDao.updatePassword(email, newPassword)
                    _passwordChanged.postValue(true)
                } else {
                    _changePasswordError.postValue("Invalid email or old password")
                }
            } catch (e: Exception) {
                _changePasswordError.postValue("Failed to change password: ${e.message}")
            }
        }
    }
}
