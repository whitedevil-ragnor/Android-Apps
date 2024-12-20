package com.meow.typesafenav.counter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CounterScreenRoute(){
    val viewModel:CounterViewModel= hiltViewModel()
    val counterState= viewModel.count.collectAsStateWithLifecycle()
    CounterScreen(counterState, increamentOnClick = {
        viewModel.increment()
    })
}
@Composable
fun CounterScreen(counterState:State<Int>,increamentOnClick:()->Unit){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Count:{${counterState.value}}")
        Button(onClick = {
            increamentOnClick()
        }) {
            Text("Increase")
        }
    }
}