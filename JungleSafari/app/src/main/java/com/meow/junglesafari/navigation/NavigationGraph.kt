package com.meow.junglesafari.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.meow.junglesafari.Home_Page
import com.meow.junglesafari.chatbot.ChatPage
import com.meow.junglesafari.mamels.animals.LionScreen
import com.meow.junglesafari.mamels.Mammals_Page

@Composable
fun NavigationScreens(navController: NavHostController){
    NavHost(navController, startDestination = Screens.Home.route) {
        composable(Screens.Home.route){
            Home_Page(navController = navController)
        }
        composable(Screens.Chatbot.route){
            ChatPage()
        }
        composable(Screens.Mammals.route){
            Mammals_Page(navController=navController)
        }
        composable(Screens.LionScreen.route){
            LionScreen()
        }
    }
}