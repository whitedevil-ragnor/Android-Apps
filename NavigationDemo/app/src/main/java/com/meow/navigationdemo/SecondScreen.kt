


package com.meow.navigationdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.meow.navigationdemo.ui.theme.NavigationDemoTheme

@Composable
fun SecondScreen(name:String,age:Int,NaviagteToThirdScreen:()->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the Second Screen", fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Welcome $name ", fontSize = 25.sp)
        Text("Age $age", fontSize = 25.sp)

        Button(onClick = {NaviagteToThirdScreen()}) {
            Text("Third Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() { // Renamed this function
    NavigationDemoTheme {
        //FirstScreen({},) // Call the actual FirstScreen here for preview
    }
}


