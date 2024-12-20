package com.meow.tunak

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MusicViewModel:ViewModel(){
    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.BottomScreen.Home)
    val currentScreen:MutableState<Screen>
        get() = _currentScreen
    fun setCurrentScreen(screen: Screen){
        _currentScreen.value=screen
    }
}