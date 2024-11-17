package com.meow.stationscout

import PlatformScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schhedule.ScheduleScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Platform : Screen("platform") // New page screen
    object Schedule : Screen("schedule") // Schedule screen
    object BookTickets : Screen("Book_Tickets")
    object Languages : Screen("Languages")
    object Settings: Screen("Settings")
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Platform.route) { PlatformScreen(navController) }
        composable(Screen.Schedule.route) { ScheduleScreen(navController) }
        composable(Screen.Schedule.route) { BookTickets(navController) }
        // Add Schedule screen
        // Add other composable routes as needed
    }
}
