package com.meow.wishlist.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.meow.wishlist.ViewModels.AuthViewModel
import com.meow.wishlist.screens.AddEditScreen
import com.meow.wishlist.screens.MainScreen
import com.meow.wishlist.ViewModels.WishViewModel
import com.meow.wishlist.repositories.AuthRepository
import com.meow.wishlist.screens.SignInScreen
import com.meow.wishlist.screens.SignUpScreen


@Composable
fun NavigationScreen(
    viewModel: WishViewModel = viewModel(),
    authViewModel: AuthViewModel=viewModel(),
    navController: NavHostController = rememberNavController()){
    var checkLogin=FirebaseAuth.getInstance().currentUser
    var startPoint=if (checkLogin!=null) Screen.HomeScreen.route else  Screen.SignUpScreen.route
    NavHost(navController = navController, startDestination = startPoint){
        composable(Screen.SignUpScreen.route){
            SignUpScreen(authViewModel=authViewModel,
                onNavigationToSignUp = {},
                navController=navController)
        }
        composable(Screen.SignInScreen.route){
           SignInScreen(viewModel = authViewModel,navController=navController)
        }
        composable(Screen.HomeScreen.route){
            MainScreen(navController,viewModel)
        }
        composable(
            route = Screen.WishScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ) { entry ->
            val id = entry.arguments?.getLong("id") ?: 0L
            AddEditScreen(id = id, viewModel = viewModel, navController = navController)
        }
    }
}