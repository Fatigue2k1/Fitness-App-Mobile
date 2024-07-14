import android.app.Application
import android.util.Log
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

    fun signUp(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // ... (existing code)

                // Handle successful sign-up
                _userCreated.postValue(true)
            } catch (e: Exception) {
                // ...
                _userCreated.postValue(false) // Indicate failure
            }
        }
    }
}