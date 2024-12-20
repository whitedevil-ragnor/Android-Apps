package com.meow.tunak.bottomScrrens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.meow.tunak.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(){
   val categories= listOf("Hits","Pop","Rock","Hip-Hop/Rap","Country","Jazz")
    val grouped= listOf<String>("New","Favourite","Top Hits").groupBy { it[0]}
    LazyColumn {
        grouped.forEach {grp->
            stickyHeader{
                Text(text = grp.value[0])
                LazyRow {
                   items(categories){item ->
                       BoxesScreen(item,icon= R.drawable.ic_account)
                   }
                }
            }
        }
    }
}

@Composable
fun BoxesScreen(cat:String,icon:Int){
    Card (modifier = Modifier.padding(20.dp).size(200.dp), border = BorderStroke(3.dp, color = Color.Black)){
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
            Text(cat, modifier = Modifier.padding(8.dp))
            Image(painterResource(icon), contentDescription = cat)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen(){
    HomeScreen()
}





