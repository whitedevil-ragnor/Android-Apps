package com.meow.typesafenav.counter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject

@HiltViewModel
class CounterViewModel@Inject constructor(
    private val counterRepository: CounterRepository,
    savedStateHandle: SavedStateHandle
):ViewModel(){
    private val _count= MutableStateFlow(0)
    val count:StateFlow<Int> = _count

     fun increment(){
        viewModelScope.launch {
            _count.value =counterRepository.increaseCounter(_count.value)
        }
    }
}