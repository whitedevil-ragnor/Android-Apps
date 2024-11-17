package com.meow.stationscout

import PlatformScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.schhedule.ScheduleScreen
import kotlinx.coroutines.launch




@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = when (currentBackStackEntry?.destination?.route) {
        Screen.Home.route -> Screen.Home
        Screen.Platform.route -> Screen.Platform
        Screen.Schedule.route -> Screen.Schedule
        Screen.Schedule.route -> Screen.BookTickets
        else -> Screen.Home
    }

    // State to control the drawer
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController,drawerState,scope)
        }
    ) {
        Scaffold(
            topBar = {
                TopNavigationBar(
                    navController = navController,
                    currentScreen = currentScreen,
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigationBar(navController) // Pass navController
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) { HomeScreen(navController) }
                composable(Screen.Platform.route) { PlatformScreen(navController) }
                composable(Screen.Schedule.route) { ScheduleScreen(navController) }
                composable(Screen.BookTickets.route) { BookTickets(navController) }

                // New Schedule screen composable
            }
        }
    }
}



