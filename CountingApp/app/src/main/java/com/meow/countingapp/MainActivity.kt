package com.meow.countingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meow.countingapp.ui.theme.CounterViewModel
import com.meow.countingapp.ui.theme.CountingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val counterViewModel: CounterViewModel by viewModels()
            CountingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterScreen(counterViewModel)
                }
            }
        }
    }
}
@Composable
fun CounterScreen(viewModel: CounterViewModel) {
    // Observe the counter value from the ViewModel
    val count by viewModel.count

    // Use Box to center the content both horizontally and vertically
    Box(
        modifier = Modifier.fillMaxSize(), // Make the Box fill the whole screen
        contentAlignment = Alignment.Center // Center content within the Box
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Count: $count", style = MaterialTheme.typography.headlineMedium)

            Row {
                Button(onClick = { viewModel.incrementCount() }, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Increment")
                }
                Button(onClick = { viewModel.decrementCount() }, modifier = Modifier.padding(8.dp)) {
                    Text(text = "Decrement")
                }
            }
        }
    }}