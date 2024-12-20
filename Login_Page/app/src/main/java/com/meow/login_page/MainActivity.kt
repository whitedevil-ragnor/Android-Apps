 package com.meow.login_page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.meow.login_page.firebaseSetUp.AuthViewModel
import com.meow.login_page.navigation.NavigationGraph
import com.meow.login_page.ui.theme.Login_PageTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController= rememberNavController()
            val authViewModel:AuthViewModel= viewModel()
            Login_PageTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.DarkGray) {
                    NavigationGraph(authViewModel = authViewModel, navController = navController)
                }
            }
        }
    }
}
