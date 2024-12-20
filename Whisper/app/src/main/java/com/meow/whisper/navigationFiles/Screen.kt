package com.meow.whisper.navigationFiles

sealed class Screen(val route:String) {
    data object Authentication:Screen("authentication")
    data object User:Screen("user")
}