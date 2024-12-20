package com.meow.wishlist.ViewModels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.meow.wishlist.Injection
import com.meow.wishlist.UiState
import com.meow.wishlist.dataclass.User
import com.meow.wishlist.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authRepository: AuthRepository

    // Use `UiState` for consistent state handling
    private val _uiState = MutableLiveData<UiState<Boolean>>()
    val uiState: LiveData<UiState<Boolean>> get() = _uiState

    init {
        authRepository = AuthRepository(
            auth = FirebaseAuth.getInstance(),
            firestore = Injection.instance()
        )
    }

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading // Show loading state

            val result = authRepository.newUser(name = name, email = email, password = password)
            if (result.isSuccess) {
                _uiState.value = UiState.Success(true)
            } else {
                _uiState.value = UiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading // Show loading state

            val result = authRepository.logIn(email, password)
            if (result.isSuccess) {
                _uiState.value = UiState.Success(true)
            } else {
                _uiState.value = UiState.Error(result.exceptionOrNull()?.message ?: "Login failed")
            }
        }
    }
}
