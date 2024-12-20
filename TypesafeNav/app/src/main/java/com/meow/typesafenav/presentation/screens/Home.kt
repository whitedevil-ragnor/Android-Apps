package com.meow.typesafenav.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.meow.typesafenav.NavGraphs
import com.meow.typesafenav.destinations.AdminRouteDestination
import com.meow.typesafenav.destinations.UserRouteDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(start = true)
fun HomeRoute(navigator: DestinationsNavigator) {
    val navArg = UserNavArgs(123, "Rudder")
    Home(
        onUserButtonClick = {
            navigator.navigate(UserRouteDestination(navArgs = navArg))
        },
        onAdminButtonClick = {
            navigator.navigate(AdminRouteDestination())
        }
    )
}

@Composable
fun Home(
    onAdminButtonClick: () -> Unit,
    onUserButtonClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding().statusBarsPadding()) {
        Text("Hey, I am at Home")
        Button(onClick = onAdminButtonClick) {
            Text("Admin")
        }
        Button(onClick = onUserButtonClick) {
            Text("User")
        }
    }
}
