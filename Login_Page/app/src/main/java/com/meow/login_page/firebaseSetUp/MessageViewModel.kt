package com.meow.login_page.firebaseSetUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.meow.login_page.screens.home.Message
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MessageViewModel:ViewModel() {
    val messageRepo:MessageRepo
    val userRepository:UserRepository
    init {
        messageRepo=MessageRepo(Injection.instance())
        userRepository=UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance())
        loadCurrentUser()
    }
    private val _messages=MutableLiveData<List<Message>>()
    val messages :LiveData<List<Message>> get() =_messages
    private val _roomId = MutableLiveData<String>()
    private val _currentUser = MutableLiveData<User>()
    val currentUser:LiveData<User> get() = _currentUser
    private fun loadCurrentUser(){
        viewModelScope.launch {
          //  userRepository.getCurrentUser()
        }
    }

}
