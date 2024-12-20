package com.meow.login_page.screens.logIn



import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import com.meow.login_page.firebaseSetUp.AuthViewModel
import com.meow.login_page.firebaseSetUp.Result




@Composable
fun LogInScreen(authViewModel: AuthViewModel, onNavigationToNewUser: () -> Unit, onSignInSuccess: () -> Unit) {
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isLoggingIn by remember { mutableStateOf(false) }

    // Observe the authentication result
    val result by authViewModel.authResult.observeAsState()

    // Use LaunchedEffect to handle login result changes
    LaunchedEffect(result) {
        when (result) {
            is Result.Success -> {
                onSignInSuccess()
                isLoggingIn = false // Re-enable the button after success
            }
            is Result.Error -> {
                val exception = (result as Result.Error).exception
                Log.e("AuthViewModel", "Login error: ${exception.message}")
                isLoggingIn = false // Re-enable the button after error
            }
            else -> {
                // Handle other cases or do nothing
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = userEmail,
            onValueChange = { newText -> userEmail = newText },
            Modifier.background(Color.White).width(250.dp),
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done)
        )
        Spacer(Modifier.height(30.dp))
        TextField(
            value = userPassword,
            onValueChange = { newText -> userPassword = newText },
            Modifier.background(Color.White).width(250.dp),
            label = { Text("Password") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    val icon: ImageVector = if (isPasswordVisible) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, autoCorrect = false, imeAction = ImeAction.Done)
        )
        Spacer(Modifier.height(48.dp))

        Button(
            onClick = {
                if (!isLoggingIn) {
                    isLoggingIn = true // Disable the button during login
                    authViewModel.login(userEmail, userPassword) // Trigger login
                }
            },
            enabled = !isLoggingIn, // Disable button while logging in
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Log In", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(5.dp))
        }

        Text(
            "Don't have an account? Sign up.",
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier
                .padding(top = 32.dp)
                .clickable { onNavigationToNewUser() }
        )
    }
}
