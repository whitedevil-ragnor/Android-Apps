package com.meow.junglesafari.navigation

sealed class Screens(val route:String) {
    data object Home:Screens("Home")
    data object Chatbot:Screens("chatbot")
    data object Mammals:Screens("mammals")
    data object LionScreen:Screens("lionScreen")
}