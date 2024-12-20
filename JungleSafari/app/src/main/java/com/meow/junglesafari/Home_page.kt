package com.meow.junglesafari

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.meow.junglesafari.navigation.Screens
import androidx.compose.foundation.layout.Box

data class CardItem(
    val name: String,
    val color: Brush,
    val onClick: () -> Unit
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home_Page(navController: NavController) {
    // List of cards with unique properties
    val cardItems = listOf(
        CardItem("Mammals", Brush.verticalGradient(listOf(Color(0xFFFFE082), Color(0xFFFFC107)))) {
            navController.navigate(Screens.Mammals.route)
        },
        CardItem("Birds", Brush.verticalGradient(listOf(Color(0xFF80DEEA), Color(0xFF26C6DA)))) { /* Action for Birds */ },
        CardItem("Reptiles", Brush.verticalGradient(listOf(Color(0xFFC5E1A5), Color(0xFF388E3C)))) { /* Action for Reptiles */ },
        CardItem("Amphibians", Brush.verticalGradient(listOf(Color(0xFFFFAB91), Color(0xFFFF7043)))) { /* Action for Amphibians */ }
    )

    Scaffold(modifier = Modifier.statusBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Jungle Safari",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6
                    )
                },
                backgroundColor = colorResource(R.color.teal_200),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screens.Chatbot.route)
                    }) {
                        Icon(painter = painterResource(R.drawable.hand_doubt), contentDescription = "doubt")
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.home_page_background)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cardItems) { cardItem ->
                Card(
                    onClick = { cardItem.onClick() },
                    modifier = Modifier
                        .height(200.dp)
                        .width(250.dp)
                        .padding(16.dp),
                    shape = MaterialTheme.shapes.medium, // Optional: Rounded corners for the cards
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(cardItem.color), // Apply the gradient here
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cardItem.name,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h5,
                            color = Color.White // Ensure the text is visible on top of the gradient
                        )
                    }
                }
            }
        }
    }
}
