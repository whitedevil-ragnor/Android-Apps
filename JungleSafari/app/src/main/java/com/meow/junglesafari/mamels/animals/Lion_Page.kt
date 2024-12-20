package com.meow.junglesafari.mamels.animals

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.meow.junglesafari.R // Your package for drawable resources


@Composable
fun LionScreen() {
    // List of points about Lion
    val lionFacts = listOf(
        "Lions are the second largest big cats in the world.",
        "A group of lions is called a pride.",
        "Lions are carnivores and they hunt in groups.",
        "Male lions have a mane, while females do not.",
        "Lions can sleep for up to 20 hours a day."
    )
    // Column layout for the image, title, and LazyColumn
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Image at the center
        Image(
            painter = painterResource(id = R.drawable.lion), // Use your lion image here
            contentDescription = "Lion Image",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        // Title text "Lion"
        Text(
            color = Color.White,
            text = "Lion",
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        // LazyColumn for lion facts
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(lionFacts) { fact ->
                Text(
                    color = Color.White,
                    text = fact,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}
