package com.meow.login_page.screens.home

import com.google.firebase.Timestamp

data class Message(
    val isSentByCurrentUser:Boolean,
    val senderFirstname:String="",
    val text:String="",
    val timestamp: Long
)
