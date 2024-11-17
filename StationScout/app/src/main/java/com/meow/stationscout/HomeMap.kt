package com.meow.stationscout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun HomeMap(){
    Box(
        modifier = Modifier
            .width(400.dp) // Square box with max width and height of 200 dp
            .padding( 16.dp)
            .background(Color.Gray, shape = RoundedCornerShape(8.dp)) // Optional background and corner radius
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_map), // Replace with your image resource
            contentDescription = "Sample Image",
            modifier = Modifier
                .width(400.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.volume), // Replace with your image resource
            contentDescription = "Sample Image",
            modifier = Modifier
                .width(50.dp).height(50.dp).padding(start = 16.dp)
        )
    }
}