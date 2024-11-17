package com.meow.stationscout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BookTickets(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(102, 0, 51),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 10.dp, vertical = 1.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(102, 0, 51) // Light blue color
                ),
                contentPadding = PaddingValues(0.dp) // Padding inside the button
            ){
                Text(text = "Reserved", style = MaterialTheme.typography.titleSmall)
            }
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(0, 204, 0),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 10.dp, vertical = 1.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0, 204, 0) // Light blue color
                ),
                contentPadding = PaddingValues(0.dp) // Padding inside the button
            ){
                Text(text = "Book", style = MaterialTheme.typography.titleSmall)
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            painter = painterResource(id = R.drawable.t_1), // Replace with your image resource ID
            contentDescription = "Platform Image",
            contentScale = ContentScale.FillBounds, // Ensures the image fills the width while cropping as needed
            modifier = Modifier
                .fillMaxWidth()  // Maximum width
                .height(100.dp) // Fixed height
                .clip(RoundedCornerShape(8.dp)) // Clips the corners with an 8.dp radius
                .border(
                    width = 2.dp, // Border thickness
                    color = Color.Red, // Border color
                    shape = RoundedCornerShape(8.dp) // Same rounded shape for the border
                )
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(102, 0, 51),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 10.dp, vertical = 1.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(102, 0, 51) // Light blue color
                ),
                contentPadding = PaddingValues(0.dp) // Padding inside the button
            ){
                Text(text = "General", style = MaterialTheme.typography.titleSmall)
            }
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(0, 204, 0),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 10.dp, vertical = 1.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0, 204, 0) // Light blue color
                ),
                contentPadding = PaddingValues(0.dp) // Padding inside the button
            ){
                Text(text = "Book", style = MaterialTheme.typography.titleSmall)
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            painter = painterResource(id = R.drawable.t_2), // Replace with your image resource ID
            contentDescription = "Platform Image",
            contentScale = ContentScale.FillBounds, // Ensures the image fills the width while cropping as needed
            modifier = Modifier
                .fillMaxWidth() // Maximum width
                .height(100.dp) // Fixed height
                .clip(RoundedCornerShape(8.dp)) // Clips the corners with an 8.dp radius
                .border(
                    width = 2.dp, // Border thickness
                    color = Color(255,128,0), // Border color
                    shape = RoundedCornerShape(8.dp) // Same rounded shape for the border
                )
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(102, 0, 51),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 10.dp, vertical = 1.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(102, 0, 51) // Light blue color
                ),
                contentPadding = PaddingValues(0.dp) // Padding inside the button
            ){
                Text(text = "Platform", style = MaterialTheme.typography.titleSmall)
            }
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .padding(4.dp) // External padding for spacing around the button
                    .background(
                        Color(0, 204, 0),
                        shape = RoundedCornerShape(8.dp)
                    ) // Light blue background
                    .padding(horizontal = 10.dp, vertical = 1.dp), // Inner padding for content
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0, 204, 0) // Light blue color
                ),
                contentPadding = PaddingValues(0.dp) // Padding inside the button
            ){
                Text(text = "Book", style = MaterialTheme.typography.titleSmall)
            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            painter = painterResource(id = R.drawable.t_3), // Replace with your image resource ID
            contentDescription = "Platform Image",
            contentScale = ContentScale.FillBounds, // Ensures the image fills the width while cropping as needed
            modifier = Modifier
                .fillMaxWidth() // Maximum width
                .height(100.dp) // Fixed height
                .clip(RoundedCornerShape(8.dp)) // Clips the corners with an 8.dp radius
                .border(
                    width = 2.dp, // Border thickness
                    color = Color(204,204,0), // Border color
                    shape = RoundedCornerShape(8.dp) // Same rounded shape for the border
                )
        )
    }
}