package com.meow.tunak.bottomScrrens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Lib(val icon: ImageVector, val name:String)
val Libr= listOf(
    Lib(
        icon =Icons.Default.AccountCircle,"icon1"
    ),
    Lib(
        icon =Icons.Default.AccountCircle,"icon1"
    ),
    Lib(
        icon =Icons.Default.AccountCircle,"icon1"
    ),
    Lib(
        icon =Icons.Default.AccountCircle,"icon1"
    )

)

@Composable
fun LibraryScreen(){
    LazyColumn {
        items(Libr){
            Library(it)
        }
    }
}
@Composable
fun Library(lib:Lib){
    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                Icon(lib.icon, contentDescription = lib.name, modifier = Modifier.padding(end=16.dp))
                Text(lib.name, modifier = Modifier.padding(8.dp))
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
        }
        Divider(color = Color.LightGray)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewLibraryScreen(){
    LibraryScreen()
}

