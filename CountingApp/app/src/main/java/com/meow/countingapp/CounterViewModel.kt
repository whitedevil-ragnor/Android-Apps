package com.meow.countingapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    // Private mutable state variable _count
    private val _count = mutableStateOf(0)

    // Public immutable state variable count
    val count: State<Int> = _count

    // Method to increment the count
    fun incrementCount() {
        _count.value++
    }

    // Method to decrement the count
    fun decrementCount() {
        if (_count.value > 0) {
            _count.value--
        }
    }
}