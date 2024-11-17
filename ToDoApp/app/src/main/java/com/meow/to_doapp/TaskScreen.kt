package com.meow.to_doapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TaskScreen(){
    Column( modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    , verticalArrangement = Arrangement.SpaceEvenly){
        Text("hello")
        TextButton(onClick = {}) {"hello 2"}
        TextButton(onClick = {}) {"hello 3" }
    }
}
