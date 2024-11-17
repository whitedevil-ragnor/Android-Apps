package com.meow.stationscout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp) // Padding around the entire column
    ) {
        // Add LocationDestinationBox
        LocationDestinationBox()

        // Add space between LocationDestinationBox and HomeMap
        Spacer(modifier = Modifier.height(8.dp)) // Adjust the space as needed

        // Add HomeMap
        HomeMap()
    }
}
@Composable
fun LocationDestinationBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Black, RoundedCornerShape(8.dp))
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // TextField for Location input with a leading icon
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Enter Location") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn, // Replace with the appropriate icon
                        contentDescription = "Location"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // TextField for Destination input with a leading icon
            TextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Enter Destination") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Face, // Replace with the appropriate icon
                        contentDescription = "Destination"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            // Button below the Destination input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center // Align the button to the right
            ) {
                Box(
                    modifier = Modifier
                        .size(width = 330.dp, height = 40.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Find My Way", color = Color.White, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}




