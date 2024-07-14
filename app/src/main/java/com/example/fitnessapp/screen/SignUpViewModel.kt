import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitnessapp.database.AppDatabase
import com.example.fitnessapp.database.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = AppDatabase.getDatabase(application).userDao()

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Check if user with this email already exists
                val existingUser = userDao.getUserByEmail(email)
                if (existingUser != null) {
                    Log.d("SignUpViewModel", "User already exists")
                    // Handle user already exists scenario
                } else {
                    // Insert new user into the database
                    userDao.insertUser(User(email = email, password = password))
                    Log.d("SignUpViewModel", "User inserted into database")
                    // Handle successful sign-up (e.g., navigate to the login screen)
                }
            } catch (e: Exception) {
                Log.e("SignUpViewModel", "Error occurred during sign-up", e)
                // Handle error scenario
            }
        }
    }
}