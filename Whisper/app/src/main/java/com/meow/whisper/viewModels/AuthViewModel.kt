package com.meow.whisper.viewModels

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.meow.whisper.AuthState
import com.meow.whisper.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel (
    private val authRepository: AuthRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Normal)
    val authState: StateFlow<AuthState> get() = _authState
    private val _verificationId = MutableStateFlow<String?>(null)  // Mutable flow to track verificationId

    fun logOut(){
        authRepository.signOut()
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
    }


    fun sendOTP(phoneNumber: String) {
        _authState.value = AuthState.Loading
        authRepository.sendVerificationCode(phoneNumber, object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.i("Verification Completed", "$credential")
                signInWithCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.e("Verification Failed", e.message.toString())
                _authState.value = AuthState.Error(e.message ?: "Verification failed")
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d("Code Sent", "onCodeSent: $id")
                _verificationId.value = id  // Store verificationId
                _authState.value = AuthState.Success("OTP sent successfully", success = null)
            }
        })
    }





    fun verifyOTP(otp: String) {
        val id = _verificationId.value
        if (id != null) {  // Ensure verificationId is initialized before proceeding
            val credential = PhoneAuthProvider.getCredential(id, otp)
            signInWithCredential(credential)
        } else {
            _authState.value = AuthState.Error("Verification ID is not set. Please resend the OTP.")
        }
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        viewModelScope.launch {
            authRepository.signInWithPhoneCredential(credential) { success, message ->
                if (success) {
                    _authState.value = AuthState.Success("Authentication successful", true)
                } else {
                    _authState.value = AuthState.Error(message ?: "Authentication failed")
                }
            }
        }
    }
}