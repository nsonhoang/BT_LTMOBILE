import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false) // Trạng thái đăng nhập
    val isLoggedIn = _isLoggedIn.asStateFlow()

    fun setLoggedIn(user: FirebaseUser) {

        _isLoggedIn.value = true
    }
    fun logout() {
        _isLoggedIn.value = false
    }


}
