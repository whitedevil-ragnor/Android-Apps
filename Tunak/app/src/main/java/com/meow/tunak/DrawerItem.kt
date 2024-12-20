package com.meow.tunak

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun DrawerItem(
    selected:Boolean,
    item:Screen.DrawerScreen,
    onDrawerItemClicked:()->Unit
){
    val background=if (selected) R.color.soft_gray else R.color.background
    Row (modifier = Modifier.fillMaxWidth().background(colorResource(background)).padding(horizontal = 8.dp, vertical = 16.dp).clickable {
        onDrawerItemClicked()
    }){
        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(top = 4.dp, end = 8.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h6
        )
    }

}