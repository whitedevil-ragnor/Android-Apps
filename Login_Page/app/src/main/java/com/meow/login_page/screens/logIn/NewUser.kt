package com.meow.login_page.screens.logIn

import android.annotation.SuppressLint
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.meow.login_page.firebaseSetUp.AuthViewModel
import com.meow.login_page.firebaseSetUp.Result
import com.meow.login_page.navigation.Screen

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewUser(authViewModel: AuthViewModel,onNavigationToSignUp:()->Unit,navController: NavController) {
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
     var password by remember { mutableStateOf("") }
    var isPasswordvisible by remember { mutableStateOf(false) }
    val result by authViewModel.authResult.observeAsState()

    val keyboardController= LocalSoftwareKeyboardController.current
    Scaffold(modifier = Modifier.fillMaxSize().padding().statusBarsPadding()) {
       Column (modifier = Modifier.fillMaxSize().background(Color.Black),
           verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){

           Text("Sign Up",Modifier.padding(20.dp), color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.titleLarge)

           TextField(value = firstname, onValueChange = { newText -> firstname = newText},Modifier.background(Color.White).width(250.dp),
               label = { Text("First name") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done))
           Spacer(Modifier.height(30.dp))

           TextField(value = lastname, onValueChange = { newText -> lastname= newText},Modifier.background(Color.White).width(250.dp),
               label = { Text("Last name") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,imeAction = ImeAction.Done))
           Spacer(Modifier.height(30.dp))

           TextField(value = email, onValueChange = { newText -> email = newText},Modifier.background(Color.White).width(250.dp),
               label = { Text("Email") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,imeAction = ImeAction.Done))
           Spacer(Modifier.height(30.dp))

           TextField(value = password, onValueChange = {  password = it},Modifier.background(Color.White).width(250.dp),
               label = { Text("Password") }, visualTransformation = if(isPasswordvisible) PasswordVisualTransformation() else VisualTransformation.None,
               trailingIcon = { IconButton(onClick = {isPasswordvisible= !isPasswordvisible}){
                    val icon:ImageVector=if(isPasswordvisible) Icons.Filled.KeyboardArrowDown else Icons.Filled.KeyboardArrowUp
                   Icon(imageVector = icon, contentDescription = null,)
               } }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, autoCorrect = false,imeAction = ImeAction.Done))

           Spacer(Modifier.height(48.dp))
           Button(
               onClick = {
                    authViewModel.newUser(email = email.trim(), password = password, firstName = firstname, lastName = lastname)
                   if(result==Result.Success(true)){
                       firstname=""
                       lastname=""
                       email=""
                       password=""
                       navController.navigate(Screen.ChatRoomHomeScreen.route)
                   }else{

                   }
               },
               colors = ButtonDefaults.buttonColors(containerColor = Color.White), // Set background color to white
               shape = RoundedCornerShape(10.dp), // Rounded corners with 20.dp radius
               modifier = Modifier
                   .padding(16.dp)
                   // Button takes up the full width
           ){
               Text("Create Account", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(5.dp))

           }
           Spacer(Modifier.height(10.dp))
           Text("Already have an account ? sign in !", color = Color.White, modifier = Modifier.padding(5.dp).clickable { onNavigationToSignUp() }, style = MaterialTheme.typography.titleMedium)
       }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewNewUser(){

}