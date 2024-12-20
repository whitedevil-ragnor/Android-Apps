package com.meow.whisper

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.meow.whisper.navigationFiles.NavigationGraph
import com.meow.whisper.repositories.AuthRepository
import com.meow.whisper.repositories.FireStoreRepository
import com.meow.whisper.repositories.UserRepository
import com.meow.whisper.screens.SignUpScreen
import com.meow.whisper.ui.theme.WhisperTheme
import com.meow.whisper.viewModels.AuthViewModel
import com.meow.whisper.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhisperTheme {
                val navController = rememberNavController()
                val sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
                val dbRepository = FireStoreRepository()
                val authRepository = AuthRepository(sharedPreferences)
                val authViewModel=AuthViewModel(
                    authRepository=authRepository,
                    sharedPreferences=sharedPreferences)
                val userRepository=UserRepository()
                val userViewModel=UserViewModel(userRepository)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        authRepository = authRepository,
                        userRepository = userRepository,
                       sharedPreferences = sharedPreferences
                    )
                }
            }
        }
    }
}
