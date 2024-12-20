package com.meow.wishlist.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.meow.wishlist.UiState
import com.meow.wishlist.dataclass.User
import kotlinx.coroutines.tasks.await

class AuthRepository (private val auth:FirebaseAuth,private val firestore: FirebaseFirestore){
    suspend fun newUser(name:String,email:String,password:String):Result<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            saveUserToFirestore(User(name=name.trim(), email = email.trim()))
            Result.success(true)
        }catch (e:Exception){
            Result.failure(e)
        }
    }
   private suspend fun saveUserToFirestore(user: User){
        firestore.collection("Users").document(user.email).set(user).await()
    }

    suspend fun logIn(email:String,password: String):Result<Boolean> =
        try {
            auth.signInWithEmailAndPassword(email,password).await()
            println("Logged in successfully")
            Result.success(true)
        }catch (e:Exception){
            println("error in log in:${e.message}")
            Result.failure(e)
        }
}