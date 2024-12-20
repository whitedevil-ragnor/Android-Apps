package com.meow.wishlist.Navigation

sealed class Screen(val route:String) {
    data object SignUpScreen: Screen("signup_screen")
    data object SignInScreen: Screen("signin_screen")
    data object HomeScreen: Screen("home_screen")
    data object WishScreen: Screen("wish_screen")
}