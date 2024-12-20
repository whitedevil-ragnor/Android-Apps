package com.meow.whisper.repositories

import android.util.Log
import com.meow.whisper.Instance
import com.meow.whisper.dataClasses.Message
import com.meow.whisper.dataClasses.User
import kotlinx.coroutines.tasks.await

class FireStoreRepository{
    private val db=Instance.FirestoreInstance()
    private val usersCollection=db.collection("Users")
//    suspend fun addMessage(message:Message):Result<Void?>{
////        return try{
////            messagesCollection.document(message.senderId).set(message)
////            Result.success(null)
////        }catch (e:Exception){
////            Result.failure(e)
////        }
////    }

    suspend fun addUser(user:User):Result<Void?>{
        return try {
            usersCollection.document(user.phoneNumber).set(user)
            Result.success(null)
        }catch (e:Exception){
            Log.i("User Error",e.localizedMessage)
            Result.failure(e)
        }
    }

//    suspend fun updateMessage(messageId:String,msg:Map<String,Any>):Result<Void?>{
//        return try {
//            messagesCollection.document(messageId).update(msg).await()
//            Result.success(null)
//        }catch (e:Exception){
//            Result.failure(e)
//        }
//    }
//    suspend fun deleteMessage(messageId: String):Result<Void?>{
//        return try {
//            messagesCollection.document(messageId).delete().await()
//            Result.success(null)
//        }catch (e:Exception){
//            Result.failure(e)
//        }
//    }
//    suspend fun loadMessages():Result<List<Message>>{
//        return try {
//            val snapshot=messagesCollection.get().await()
//            val messagesList=snapshot.toObjects(Message::class.java)
//            Result.success(messagesList)
//        }catch (e:Exception){
//            Result.failure(e)
//        }
//    }
}