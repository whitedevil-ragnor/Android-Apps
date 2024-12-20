package com.meow.whisper.screens

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.meow.whisper.AuthState
import com.meow.whisper.R
import com.meow.whisper.dataClasses.User
import com.meow.whisper.navigationFiles.Screen
import com.meow.whisper.repositories.AuthRepository
import com.meow.whisper.repositories.UserRepository
import com.meow.whisper.viewModels.AuthViewModel
import com.meow.whisper.viewModels.UserViewModel

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    userViewModel: UserViewModel,
    sharedPreferences: SharedPreferences
) {
    // Use your factory to provide ViewModels


    var userName by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var otp by rememberSaveable { mutableStateOf("") }
    var msg by rememberSaveable { mutableStateOf("Result") }
    val authState by viewModel.authState.collectAsState()

    Column(
        Modifier.fillMaxSize().background(colorResource(R.color.signup)).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo_signup),
            contentDescription = "logo",
            modifier = Modifier.padding(top = 8.dp).size(120.dp)
        )

        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            placeholder = { Text("Name", fontWeight = FontWeight(400), color = Color.Gray, fontSize = 20.sp) },
            shape = RoundedCornerShape(10.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            placeholder = { Text("Mobile Number", fontWeight = FontWeight(400), color = Color.Gray, fontSize = 20.sp) },
            shape = RoundedCornerShape(10.dp)
        )

        Text("Send OTP",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 32.dp).clickable {
                try {
                    viewModel.sendOTP(phoneNumber = phoneNumber)
                } catch (e: Exception) {
                    msg = e.message.toString()
                }
            }
        )

        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it },
            placeholder = { Text("Enter OTP", fontWeight = FontWeight(400), color = Color.Gray) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(top = 32.dp)
        )

        Button(
            onClick = {
                try {
                    viewModel.verifyOTP(otp)
                } catch (e: Exception) {
                    msg = e.message.toString()
                }
            },
            modifier = Modifier.padding(top = 32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.teal_200),
                disabledContainerColor = Color.Gray
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Sign Up", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Text(
            modifier = Modifier.padding(top = 32.dp),
            text = msg, style = MaterialTheme.typography.h6,
            fontSize = 25.sp
        )

        when (authState) {
            is AuthState.Error -> msg = (authState as AuthState.Error).error
            AuthState.Loading -> CircularProgressIndicator()
            AuthState.Normal -> {}
            is AuthState.Success -> {
                if ((authState as AuthState.Success).success == true) {
                    try {
                        navController.navigate(Screen.User.route)
                        userViewModel.addFriend(User(name = userName, phoneNumber = phoneNumber))
                    } catch (e: Exception) {
                        Log.i("pro exception", e.localizedMessage)
                    }
                    msg = (authState as AuthState.Success).message
                }
            }
        }
    }
}
