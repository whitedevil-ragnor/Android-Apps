package com.meow.whisper.repositories

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.concurrent.TimeUnit

class AuthRepository(private val sharedPreferences: SharedPreferences) {
    private val auth:FirebaseAuth=FirebaseAuth.getInstance()

    fun saveUserLoggedInState(isLoggedIn: Boolean) {
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
    fun signOut(){
       auth.signOut()
    }
    fun sendVerificationCode(phoneNumber:String,callbacks:PhoneAuthProvider.OnVerificationStateChangedCallbacks){
        val options=PhoneAuthOptions.newBuilder(auth).
                setPhoneNumber("+91$phoneNumber").
                setTimeout(60L,TimeUnit.SECONDS).
                setCallbacks(callbacks).
                build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    fun signInWithPhoneCredential(
        credential: PhoneAuthCredential,
        onComplete:(Boolean,String?)->Unit
    ){
        auth.signInWithCredential(credential).addOnCompleteListener { task->
            if (task.isSuccessful){
                saveUserLoggedInState(true)
                onComplete(true,null)
            }else{
                onComplete(false,task.exception?.message)
            }
        }
    }
}