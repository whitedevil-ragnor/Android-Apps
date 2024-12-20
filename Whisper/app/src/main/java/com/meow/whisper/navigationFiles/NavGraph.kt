package com.meow.whisper.navigationFiles

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.meow.whisper.repositories.AuthRepository
import com.meow.whisper.repositories.UserRepository
import com.meow.whisper.screens.SignUpScreen
import com.meow.whisper.screens.UserScreen
import com.meow.whisper.viewModels.AuthViewModel
import com.meow.whisper.viewModels.UserViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    authRepository: AuthRepository,
    userRepository: UserRepository,
    sharedPreferences: SharedPreferences
) {
    // Check if the user is logged in
    val isLoggedIn = authRepository.isUserLoggedIn()

    // Set the start destination based on the login state
    val startDestination = if (isLoggedIn) {
        Screen.User.route // Navigate to UserScreen if logged in
    } else {
        Screen.Authentication.route // Navigate to SignUpScreen if not logged in
    }


    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.Authentication.route) {
            SignUpScreen(navController,authRepository,userRepository, sharedPreferences =sharedPreferences )
        }
        composable(Screen.User.route) {
            UserScreen(navController = navController, authRepository = authRepository, userRepository = userRepository)
        }
    }
}
