package com.meow.whisper.dataClasses

data class User(
val name: String = "",
val phoneNumber: String = "",
val friends: List<String> = listOf()
)
