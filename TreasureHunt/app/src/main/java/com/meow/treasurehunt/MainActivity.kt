package com.meow.treasurehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meow.treasurehunt.ui.theme.TreasureHuntTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreasureHuntTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    treasure()
                }
            }
        }
    }
}

@Composable
fun treasure(){
 val treasureCount= remember{ mutableStateOf(0)}
    val direction = remember{ mutableStateOf("North") }
    val Hp= remember { mutableStateOf(200)}
    val result= remember { mutableStateOf("You Continue Sailing") }
    Column {
        Text("Treasure Found : ${treasureCount.value}")
        Text("Direction : ${direction.value}")
        Text("Hp Left : ${Hp.value}")
        Button(onClick = {
            direction.value="North"
            if (Hp.value==0 && treasureCount.value<=15){
                result.value="You Lost"
                Hp.value=200
                treasureCount.value=0
            }
            if (Hp.value!=0 && treasureCount.value>15){
                result.value="You Won"
                Hp.value=200
                treasureCount.value=0
            }
            else if (Random.nextBoolean()){
                treasureCount.value+=1
            }else{
                if (Hp.value<10){
                    Hp.value=0
                }else{
                    Hp.value-=10
                }
            }
        }) {
            Text(text = "North")
        }

        Button(onClick = {
            direction.value="South"
            if (Random.nextBoolean()){
                treasureCount.value+=1
            }else{
                if (Hp.value<10){
                    Hp.value=0
                }else{
                    Hp.value-=10
                }
            }
        }) {
            Text(text = "South")
        }
        Button(onClick = {
            direction.value="Stoped"
            treasureCount.value=0
        }) {
            Text(text = "Reset")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "${result.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun treasurePreview() {
    TreasureHuntTheme {
        treasure()
    }
}