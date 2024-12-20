package com.meow.whisper.repositories

import android.util.Log
import com.meow.whisper.Instance
import com.meow.whisper.dataClasses.User

class UserRepository {
    private val db=Instance.FirestoreInstance()
    private val usersCollection=db.collection("Users")
    suspend fun addUser(user: User):Result<Void?>{
        return try {
            usersCollection.document(user.phoneNumber).set(user)
            Result.success(null)
        }catch (e:Exception){
            Log.i("User Error",e.localizedMessage)
            Result.failure(e)
        }
    }
}