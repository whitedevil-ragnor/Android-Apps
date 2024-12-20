package com.meow.navigationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import com.meow.navigationdemo.ui.theme.NavigationDemoTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstscreen") {

        // FirstScreen navigation with two parameters: name and age
        composable("firstscreen") {
            FirstScreen { name, age ->
                // Corrected route with proper separator ("/") between name and age
                navController.navigate("secondscreen/$name/$age")
            }
        }

        // Corrected composable for second screen with proper curly braces for both name and age parameters
        composable("secondscreen/{name}/{age}") { backStackEntry ->
            // Fetching name as a string
            val name = backStackEntry.arguments?.getString("name") ?: "no name"

            // Fetching age as a string and converting to Int (age comes as a String)
            val age = backStackEntry.arguments?.getString("age")?.toInt() ?: 0

            SecondScreen(name, age) {
                navController.navigate("thirdscreen")
            }
        }

        // ThirdScreen composable
        composable("thirdscreen") {
            ThirdScreen {
                navController.navigate("firstscreen")
            }
        }
    }
}
