package com.example.fitnessapp.screen.signup_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import com.example.fitnessapp.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val _userCreated = MutableLiveData<Boolean>()
    val userCreated: LiveData<Boolean> get() = _userCreated

    private val _userExists = MutableLiveData<Boolean>(false)
    val userExists: LiveData<Boolean> get() = _userExists

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val existingUser = userDao.getUserByEmail(email)
                if (existingUser != null) {
                    _userExists.postValue(true)
                } else {
                    // Insert new user into the database
                    userDao.insertUser(User(email = email, password = password))
                    _userCreated.postValue(true)
                }
            } catch (e: Exception) {
                // ...
            }
        }
    }
}