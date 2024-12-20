package com.meow.typesafenav.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun AdminRoute(){
    Admin()
}
@Composable
fun Admin(){
    Column (
        modifier = Modifier.fillMaxSize().
    statusBarsPadding()){
    }
}