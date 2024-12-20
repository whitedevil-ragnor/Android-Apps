package com.meow.junglesafari.mamels

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.meow.junglesafari.R
import com.meow.junglesafari.navigation.Screens

data class AnimalCard(
    val name: String,
    val imageRes: Int, // Resource ID for the background image
    val route: String // Add route for navigation
)

@Composable
fun Mammals_Page(navController: NavController) {

    // List of animals for the cards
    val animals = listOf(
        AnimalCard(
            "Lion", R.drawable.lion, Screens.LionScreen.route
        ), // Replace with actual image resource and screen route
        AnimalCard("Elephant", R.drawable.elephant, "Screens.Elephant.route"),
        AnimalCard("Bear", R.drawable.bear, "Screens.Bear.route"),
        AnimalCard("Dolphin", R.drawable.dolphin, "Screens.Dolphin.route"),
        AnimalCard("Kangaroo", R.drawable.kangaroo, "Screens.Kangaroo.route"),
        AnimalCard("Rabbit", R.drawable.rabit, "Screens.Rabbit.route")
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 cards per row
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
    ) {
        items(animals) { animal ->
            Card(modifier = Modifier
                .padding(8.dp)
                .aspectRatio(1f), // Make the cards square
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                onClick = {
                    navController.navigate(animal.route)
                }) {
                // Background image with name on top
                Box(modifier = Modifier.fillMaxSize()) {
                    // Background image
                    Image(
                        painter = painterResource(animal.imageRes),
                        contentDescription = "${animal.name} image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    // Name text centered on top
                    Text(
                        text = animal.name,
                        modifier = Modifier.align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        color = Color.White, // Adjust color as needed for visibility
                        style = MaterialTheme.typography.h6// Adjust style as needed
                    )
                }
            }
        }
    }
}
