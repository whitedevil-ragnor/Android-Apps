package com.meow.stationscout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    navController: NavController,
    currentScreen: Screen,
    onMenuClick: () -> Unit // Pass a lambda function for the click event
) {
    Column {
        // TopAppBar with a Menu icon as the navigation icon
        TopAppBar(
            title = {
                Text(text = "") // Display the current screen title
            },
            navigationIcon = {
                IconButton(onClick = onMenuClick) {
                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                }
            },
            actions = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.padding(end = 16.dp) // Adjust padding as needed
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn, // Location icon
                        contentDescription = "Location",
                        tint = MaterialTheme.colorScheme.onPrimary // Set icon color
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Bhubaneswar",
                            color = Color.White
                        )
                        Text(
                            text = "Current Station",
                            color = MaterialTheme.colorScheme.onPrimary,
                            style = MaterialTheme.typography.bodySmall // Adjust text style if needed
                        )
                    }// Add some space between the icon and text

                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black, // Set the background color to black
                titleContentColor = Color.White, // Set title color to white for contrast
                navigationIconContentColor = Color.White // Set icon color to white for contrast
            )
        )
    }
}

