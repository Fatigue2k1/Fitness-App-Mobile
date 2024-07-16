package com.example.fitnessapp.screen.login_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import com.example.fitnessapp.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val _loginSuccessful = MutableLiveData<Boolean>()
    val loginSuccessful: LiveData<Boolean> get() = _loginSuccessful

    private val _loginError = MutableLiveData<String>()
    val loginError: LiveData<String> get() = _loginError

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val user = userDao.getUserByEmail(email)
                if (user != null && user.password == password) {
                    _loginSuccessful.postValue(true)
                } else {
                    _loginError.postValue("Invalid credentials")
                }
            } catch (e: Exception) {
                _loginError.postValue("An error occurred")
            }
        }
    }
}
