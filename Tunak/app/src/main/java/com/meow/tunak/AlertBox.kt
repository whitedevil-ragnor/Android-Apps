package com.meow.tunak

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.AlertDialog
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun AlertBox(showBox:MutableState<Boolean>){
    if (showBox.value){
        AlertDialog(
            onDismissRequest = {
                showBox.value=false
            },
            confirmButton = {
                TextButton(onClick = {
                    showBox.value=false
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showBox.value=false
                }) {
                    Text("Dismiss")
                }
            },
            title = {Text("Add Account", style = MaterialTheme.typography.h6)},
            text = {
                Column (modifier = Modifier.wrapContentHeight().padding(top=16.dp), verticalArrangement = Arrangement.Center){
                    TextField(
                        value = "", onValueChange = {}, label = { Text("Email") }, modifier = Modifier.padding(top=16.dp)
                    )
                    TextField(
                        value = "", onValueChange = {}, label = { Text("Password") }, modifier = Modifier.padding(top=16.dp)
                    )
                }
            }
            , modifier = Modifier.fillMaxWidth().background(colorResource(R.color.background)).padding(8.dp)
            , properties = DialogProperties(
                dismissOnClickOutside = true,
                dismissOnBackPress = true
            ),
            shape = RoundedCornerShape(10.dp)
        )
    }
}