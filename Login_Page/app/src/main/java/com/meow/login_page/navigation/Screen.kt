package com.meow.login_page.navigation

sealed class Screen(val route:String) {
    data object NewUserScreen: Screen("newUser")
    data object SignUpScreen: Screen("signUp")
    data object ChatRoomHomeScreen: Screen("chatRoomHome")
    data object ChatScreen: Screen("chatScreen")
}