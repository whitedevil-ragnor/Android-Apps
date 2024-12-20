package com.meow.weatherapp

import androidx.activity.compose.BackHandler

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.meow.weatherapp.retrofit.WeatherResponse

@Composable
fun HomeScreen(viewModel: AppViewModel){
    val WeatherResponse=viewModel.weatherResponse.observeAsState()
    var showDialog by remember { mutableStateOf(false) }
    var city by remember { mutableStateOf("") }
    BackHandler {
        showDialog=true
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Exit App")
            },
            text = {
                Text("Are you sure you want to close the app?")
            },
            confirmButton = {
                Button(onClick = {
                    // Close the app
                    showDialog = false
                    android.os.Process.killProcess(android.os.Process.myPid())
                }) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("No")
                }
            }
        )
    }


    Column (Modifier.fillMaxSize().statusBarsPadding().background(colorResource(R.color.success_background)), horizontalAlignment = Alignment.CenterHorizontally){
        Row(Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
           OutlinedTextField(value = city, onValueChange = {city=it}, modifier = Modifier.weight(1f).padding(8.dp),
               label = { Text("Search", color = Color.DarkGray) }, singleLine = true, shape = RoundedCornerShape(10.dp)
           )
            IconButton(
                onClick = {
                    viewModel.getData(city = city)
                    city = ""
                },
                modifier = Modifier
                    .weight(0.2f)
                    .size(100.dp) // Set size for IconButton
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search",
                    modifier = Modifier.size(80.dp) // Set size for Icon
                )
            }

        }
        when(val result=WeatherResponse.value){
            is NetworkResponse.Error ->
                FailedScreen(result.message)
            NetworkResponse.Loading ->
                CircularProgressIndicator()
            is NetworkResponse.Success ->
                SuccessScreen(result.data)
            null -> {}
        }
    }
}

@Composable
fun FailedScreen(msg:String){
    Column (Modifier.fillMaxSize().background(colorResource(R.color.success_background))
    , verticalArrangement =Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){
        Text(msg, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(32.dp), fontWeight = FontWeight.SemiBold)
        Image(imageVector = Icons.Default.Face, contentDescription = null,Modifier.size(200.dp))
    }
}

@Composable
fun SuccessScreen(data:WeatherResponse){
    val imageUrl="https:"+data.current.condition.icon
    val painter= rememberAsyncImagePainter(imageUrl)
    Column (modifier = Modifier.fillMaxSize().background(colorResource(R.color.success_background)),
        verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){
        Row (Modifier.fillMaxWidth().padding(top=32.dp), horizontalArrangement = Arrangement.Center){
            Icon(Icons.Default.LocationOn, contentDescription = "location",Modifier.size(40.dp))
            Text(text = data.location.name, style =  MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.SemiBold)
            Text(text = data.location.country, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(start = 16.dp), fontWeight = FontWeight.Bold)
        }
        Text(text= "${data.current.temp_c}° Cel", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top=16.dp), fontSize = 45.sp)
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator() // Show loading indicator
        }
        Image(painter = painter, contentDescription = "image",Modifier.size(140.dp))
        Text(data.current.condition.text, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight(450))
        Card (modifier = Modifier.height(400.dp).width(380.dp).padding(top=48.dp), backgroundColor = colorResource(R.color.Card), shape = RoundedCornerShape(10.dp)
        ){
            Column (Modifier.fillMaxSize()){
                Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp,end=16.dp, bottom = 8.dp), horizontalArrangement = Arrangement.SpaceAround) {
                    Card (modifier = Modifier.height(150.dp).width(150.dp), backgroundColor = colorResource(R.color.Boxes), shape = RoundedCornerShape(10.dp)){
                        Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(data.current.temp_f+"° F", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight(700), fontSize = 30.sp, color = Color.White)
                            Text("Fahrenheit", fontSize = 20.sp, color = Color.White)
                        }

                    }
                    Card (modifier = Modifier.height(150.dp).width(150.dp), backgroundColor = if (data.current.is_day==1) colorResource(R.color.day) else colorResource(R.color.night), shape = RoundedCornerShape(10.dp)){
                        val day=if (data.current.is_day==1) "Day" else "Night"
                        Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(day, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight(700), fontSize = 30.sp,
                                color = if (data.current.is_day==1) Color.Black else Color.White
                            )

                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp,end=16.dp, bottom = 16.dp), horizontalArrangement = Arrangement.SpaceAround) {
                    Card (modifier = Modifier.height(150.dp).width(150.dp), backgroundColor = colorResource(R.color.Boxes), shape = RoundedCornerShape(10.dp)){
                        Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(data.current.humidity, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight(700), fontSize = 30.sp, color = Color.White)
                            Text("Humidity", fontSize = 20.sp, color = Color.White)
                        }
                    }
                    Card (modifier = Modifier.height(150.dp).width(150.dp), backgroundColor = colorResource(R.color.Boxes), shape = RoundedCornerShape(10.dp)){
                        Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                            Text(data.current.wind_kph+" Kmh", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight(700), fontSize = 30.sp, color = Color.White)
                            Text("Wind Speed", fontSize = 20.sp, color = Color.White)
                        }
                    }
                }
            }

        }

    }
}