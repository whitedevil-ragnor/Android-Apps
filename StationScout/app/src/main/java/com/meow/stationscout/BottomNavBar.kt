package com.meow.stationscout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Button for "Platform"
        Button(
            onClick = {
                navController.navigate(Screen.Platform.route)
            },
            modifier = Modifier
                .padding(2.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                "Platform",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(23.dp, 1.dp, 23.dp, 1.dp)
            )
        }

        // Home icon button in the center
        IconButton(
            onClick = { navController.navigate(Screen.Home.route) }, // Navigate to Home
            modifier = Modifier.size(40.dp).background(Color.White)
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }

        // Button for "Schedule"
        Button(
            onClick = {
                navController.navigate(Screen.Schedule.route) // Navigate to Schedule screen
            },
            modifier = Modifier
                .padding(2.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                "Schedule",
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(22.dp, 1.dp, 22.dp, 1.dp)
            )
        }
    }
}

