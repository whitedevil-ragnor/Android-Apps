package com.meow.login_page.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.meow.login_page.firebaseSetUp.AuthViewModel
import com.meow.login_page.screens.home.ChatRoomHomeScreen
import com.meow.login_page.screens.home.ChatScreen
import com.meow.login_page.screens.logIn.NewUser
import com.meow.login_page.screens.logIn.LogInScreen

@Composable
fun NavigationGraph(navController: NavHostController,authViewModel: AuthViewModel){
    NavHost(navController, startDestination = Screen.NewUserScreen.route) {
        composable(Screen.NewUserScreen.route){
            NewUser (authViewModel, navController = navController, onNavigationToSignUp = { navController.navigate(Screen.SignUpScreen.route) } )
        }
        composable(Screen.SignUpScreen.route){
            LogInScreen(authViewModel = authViewModel, onSignInSuccess ={ navController.navigate(Screen.ChatRoomHomeScreen.route)}, onNavigationToNewUser = { navController.navigate(Screen.NewUserScreen.route) })
        }
        composable(Screen.ChatRoomHomeScreen.route){
            ChatRoomHomeScreen {
                navController.navigate("${Screen.ChatScreen.route}/${it.id}") {
                }
            }
        }
        composable("${Screen.ChatScreen.route}/{roomId}"){
            val roomId:String= it.arguments?.getString("roomId") ?:""
            ChatScreen(roomId = roomId)
        }
    }
}