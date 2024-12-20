package com.meow.hiltinjection.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meow.hiltinjection.data.remote.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository) :ViewModel(){
    private val _isCalled=MutableStateFlow<String>("")
    val isCalled:StateFlow<String> =_isCalled
}