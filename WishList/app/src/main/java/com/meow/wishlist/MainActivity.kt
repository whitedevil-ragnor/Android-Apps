package com.meow.wishlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.meow.wishlist.Navigation.NavigationScreen
import com.meow.wishlist.Navigation.Screen
import com.meow.wishlist.ViewModels.AuthViewModel
import com.meow.wishlist.screens.SignInScreen
import com.meow.wishlist.screens.SignUpScreen
import com.meow.wishlist.ui.theme.WishListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WishListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationScreen()
                }
            }
        }
    }
}

