package com.meow.chatboatdemo

import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatViewModel:ViewModel(){
    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }
    val generativeModel:GenerativeModel= GenerativeModel(
        modelName ="gemini-1.5-flash-001",
        apiKey = Constants.API_KEY
    )
    fun sendMessage(question:String){
        viewModelScope.launch {
       try {
               val chat=generativeModel.startChat(
                   history = messageList.map {
                       content(it.role){
                           text(it.message)
                       }
                   }.toList()
               )
               messageList.add(MessageModel(question,"user"))
               messageList.add(MessageModel("Typing...","model"))
               val response=chat.sendMessage(question)
               messageList.removeAt(messageList.size - 1)
               messageList.add(MessageModel(response.text.toString(),"model"))

       }catch (e:Exception){
           messageList.removeAt(messageList.size - 1)
           messageList.add(MessageModel( e.message.toString()+"Check internet connection","model"))
       }}
    }
}