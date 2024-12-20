package com.meow.typesafenav.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.meow.typesafenav.destinations.CounterScreenRouteDestination
import com.meow.typesafenav.destinations.UserRouteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(navArgsDelegate = UserNavArgs::class)
fun UserRoute(navArgs: UserNavArgs,navigator: DestinationsNavigator) {
    User(navArgs, onCounterClick = {
        navigator.navigate(CounterScreenRouteDestination())
    })
}

@Composable
fun User(navArgs: UserNavArgs,onCounterClick:()->Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Hey, I am a presenter")
        Text("ID: ${navArgs.id}, Name: ${navArgs.name}")
        Button(onClick = {
            onCounterClick()
        }) { Text("Counter") }
    }
}
