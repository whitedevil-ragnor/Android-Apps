package com.meow.tunak.bottomScrrens

import android.provider.Browser
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BrowserScreen(){
    val categories= listOf("Hits","Pop","Rock","Hip-Hop/Rap","Country","Jazz")
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(categories){
            Card(modifier = Modifier.size(200.dp).padding(20.dp), border = BorderStroke(3.dp, color = Color.Black)) {
               Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
                   Text(it, style = MaterialTheme.typography.titleMedium)
               }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBrowserScreen(){
    BrowserScreen()
}
