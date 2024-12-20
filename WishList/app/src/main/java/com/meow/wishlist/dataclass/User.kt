package com.meow.wishlist.dataclass

data class User(
    var name:String="",
    var email:String="",
    var wishlistItems: List<Task> = listOf()
)
data class Task(
    var title:String="",
    var content:String=""
)
