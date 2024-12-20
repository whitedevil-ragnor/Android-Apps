package com.meow.tunak

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.meow.tunak.bottomScrrens.BrowserScreen
import com.meow.tunak.bottomScrrens.HomeScreen
import com.meow.tunak.bottomScrrens.LibraryScreen


@Composable
fun Navigation(navController: NavController, viewModel: MusicViewModel, pd:PaddingValues){
    NavHost(navController = navController as NavHostController,
        startDestination = Screen.BottomScreen.Home.route, modifier = Modifier.padding(pd)) {
        composable(Screen.BottomScreen.Home.route){
            HomeScreen()
        }
        composable(Screen.BottomScreen.Library.route){
            LibraryScreen()
        }
        composable(Screen.BottomScreen.Browse.route){
            BrowserScreen()
        }

        composable(Screen.DrawerScreen.account.route){
            AccountScreen()
        }
        composable(Screen.DrawerScreen.subscription.route){
            SubscriptionScreen()

        }

    }

}