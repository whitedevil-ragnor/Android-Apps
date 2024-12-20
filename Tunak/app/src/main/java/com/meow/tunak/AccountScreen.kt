package com.meow.tunak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AccountScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        Row (modifier = Modifier.fillMaxWidth().background(colorResource(R.color.background)), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Row {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.AccountCircle, contentDescription = null)
                }
                Column {
                    Text("Rudra Narayan rath", style = MaterialTheme.typography.body1)
                    Text("@console.rudra", style = MaterialTheme.typography.subtitle2)
                }
            }
            IconButton(onClick = {}) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
            }

        }
        Row(modifier = Modifier.fillMaxWidth().background(colorResource(R.color.background)).padding(14.dp))
        {
         Icon(painter = painterResource(R.drawable.baseline_library_music_24), contentDescription = null,modifier = Modifier.padding(end=8.dp))
            Text("Local Music",modifier = Modifier.padding(start = 8.dp) )
        }
    }
}