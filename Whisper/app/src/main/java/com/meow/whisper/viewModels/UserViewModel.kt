package com.meow.whisper.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meow.whisper.dataClasses.User
import com.meow.whisper.repositories.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    fun addFriend(user: User) {
        viewModelScope.launch {
            userRepository.addUser(user)
        }
    }
}
