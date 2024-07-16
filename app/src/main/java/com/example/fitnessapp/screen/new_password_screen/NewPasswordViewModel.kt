//package com.example.fitnessapp.screen.new_password_screen
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.fitnessapp.database.AppDatabase
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//class NewPasswordViewModel(application: Application) : AndroidViewModel(application) {
//
//    private val userDao = AppDatabase.getDatabase(application).userDao()
//    private val _passwordReset = MutableLiveData<Boolean>()
//    val passwordReset: LiveData<Boolean> get() = _passwordReset
//
//    fun resetPassword(email: String, newPassword: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val user = userDao.getUserByEmail(email)
//                if (user != null) {
//                    user.password = newPassword
//                    userDao.insertUser(user)
//                    _passwordReset.postValue(true)
//                } else {
//                    _passwordReset.postValue(false)
//                }
//            } catch (e: Exception) {
//                _passwordReset.postValue(false)
//            }
//        }
//    }
//}
