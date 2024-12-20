package com.meow.tunak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp


@Composable
fun SubscriptionScreen(){
    Column(modifier = Modifier.fillMaxWidth().height(400.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Manage Subscription", style = androidx.compose.material3.MaterialTheme.typography.titleMedium, modifier = Modifier.padding(top=16.dp).width(200.dp))
        Card(modifier = Modifier.padding(16.dp).background(colorResource(R.color.background)), elevation = 40.dp) {
            Column {
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Column {
                        Text("Musical")
                        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Free Plan")
                           Row {
                               Text("See All Plans", color = Color.Blue, modifier = Modifier.padding(top=8.dp))
                               Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null,modifier = Modifier.padding(top=5.dp))
                           }
                        }
                    }
                }
                Divider(thickness = 2.dp)
                Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Icon(Icons.Filled.AccountBox, contentDescription = null)
                    Text("Get A Plan", style = MaterialTheme.typography.button, modifier = Modifier.padding(4.dp))
                }
            }
        }

    }
}