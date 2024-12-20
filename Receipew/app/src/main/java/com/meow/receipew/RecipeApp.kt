package com.meow.receipew

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel: MainViewModel = viewModel()  // Obtain the ViewModel instance
    val viewstate by recipeViewModel.categoriesState  // Observing the state

    // NavHost to manage the navigation flow between screens
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route) {

        // RecipeScreen Composable
        composable(Screen.RecipeScreen.route) {
            RecipeScreen(viewstate = viewstate, navigateToDetail = { category -> // Corrected: 'category' passed as a lambda argument
                // Save the selected category in the savedStateHandle for retrieval in the next screen
                navController.currentBackStackEntry?.savedStateHandle?.set("cat", category)
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        // DetailScreen Composable
        composable(Screen.DetailScreen.route) {
            // Retrieve the category object from the previousBackStackEntry's savedStateHandle
            val category = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Category>("cat") ?: Category("", "", "", "")

            // Display the CategoryDetailScreen
            CategoryDetailScreen(category = category)
        }
    }
}
