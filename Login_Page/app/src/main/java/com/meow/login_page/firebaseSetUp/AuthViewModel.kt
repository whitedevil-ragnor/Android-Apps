package com.meow.login_page.firebaseSetUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class AuthViewModel:ViewModel() {
    private val userRepository:UserRepository
    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
    }


    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun newUser(firstName:String,lastName:String,email:String,password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.newUser(firstName = firstName, lastName = lastName, email = email, password = password)
        }
    }
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.logIn(email, password)
        }
    }

}
