package com.meow.navigationdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meow.navigationdemo.ui.theme.NavigationDemoTheme

@Composable
fun FirstScreen(NaviagteToSecondScreen:(String,Int)->Unit) {
    val name = remember { mutableStateOf("") }
    val age= remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the First Screen", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = name.value, onValueChange = {
            name.value=it
        }, placeholder = { Text("Name") })
        OutlinedTextField(value = age.value, onValueChange = {
            if (it.all { char -> char.isDigit() }) {
                age.value = it
            }
        }, placeholder = { Text("Age") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {NaviagteToSecondScreen(name.value,age.value.toInt())}) {
            Text("Second page")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() { // Renamed this function
    NavigationDemoTheme {
    //    FirstScreen() // Call the actual FirstScreen here for preview
    }
}