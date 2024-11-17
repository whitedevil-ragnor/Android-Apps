package com.meow.stationscout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(navController: NavHostController,
                  drawerState: DrawerState,
                  coroutineScope: CoroutineScope = rememberCoroutineScope()) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f) // Adjust the width to cover half the screen
            .background(Color.Gray)
    ) {
        // White box container at the top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp) // Adjust the height as needed
                .background(Color.White)
                .padding(16.dp), // Optional padding inside the box
            contentAlignment = Alignment.Center // Center the items inside the box
        ) {
            // Add logo or other items inside the box
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(150.dp) // Adjust the size of the logo
                        .padding(bottom = 16.dp) // Add some spacing below the logo
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 16.dp))

        // Drawer items with navigation
        DrawerItem(icon = Icons.Default.CheckCircle, label = "Book Tickets") {
            navController.navigate(Screen.BookTickets.route)
            coroutineScope.launch {
                drawerState.close()
            }
        }
        DrawerItem(icon = Icons.Default.Info, label = "Languages") {
            navController.navigate(Screen.Platform.route) // Navigate to Platform screen
        }
        DrawerItem(icon = Icons.Default.Settings, label = "Settings") {
            navController.navigate(Screen.Schedule.route) // Navigate to Schedule screen
        }

        // Add more drawer items as needed, navigating to appropriate routes
    }
}

@Composable
fun DrawerItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }, // Handle click events
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
