package com.meow.whisper

sealed class AuthState{
    object Normal:AuthState()
    object Loading : AuthState()
    data class Success(val message: String,val success: Boolean?) : AuthState()
    data class Error(val error: String) : AuthState()
}