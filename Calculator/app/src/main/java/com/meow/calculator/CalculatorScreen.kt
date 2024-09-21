package com.meow.calculator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {
    val displayValue by viewModel.displayData.observeAsState("")
   Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween){
       Text(text = "Calculator",  // Title
           fontSize = 32.sp,  // Font size for the title
           modifier = Modifier.padding(48.dp), fontWeight = FontWeight.Bold )
       Column(modifier = Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally) {
           Text(text=displayValue, fontSize = 38.sp)
           Spacer(modifier = Modifier.height(36.dp))
           Button(onClick = {viewModel.onBackClick()},modifier = Modifier.padding(10.dp)) { Text("Back") }
           Row {
               Button(onClick = {viewModel.onOperatorClicked("+")}, modifier = Modifier.padding(10.dp)) { Text(" + ") }
               Button(onClick = {viewModel.onOperatorClicked("-")}, modifier = Modifier.padding(10.dp)) { Text(" - ") }
               Button(onClick = {viewModel.onOperatorClicked("*")}, modifier = Modifier.padding(10.dp)) { Text(" * ") }
               Button(onClick = {viewModel.onOperatorClicked("/")}, modifier = Modifier.padding(10.dp)) { Text(" / ") }
           }
           Row {
               Button(onClick = {viewModel.onNumberClicked("0")}, modifier = Modifier.padding(10.dp)) { Text(" 0 ") }
               Button(onClick = {viewModel.onNumberClicked("1")}, modifier = Modifier.padding(10.dp)) { Text(" 1 ") }
               Button(onClick = {viewModel.onNumberClicked("2")}, modifier = Modifier.padding(10.dp)) { Text(" 2 ") }
               Button(onClick = {viewModel.onNumberClicked("3")}, modifier = Modifier.padding(10.dp)) { Text(" 3 ") }
           }
           Row {
               Button(onClick = {viewModel.onNumberClicked("4")}, modifier = Modifier.padding(10.dp)) { Text(" 4 ") }
               Button(onClick = {viewModel.onNumberClicked("5")}, modifier = Modifier.padding(10.dp)) { Text(" 5 ") }
               Button(onClick = {viewModel.onNumberClicked("6")}, modifier = Modifier.padding(10.dp)) { Text(" 6 ") }
               Button(onClick = {viewModel.onNumberClicked("7")}, modifier = Modifier.padding(10.dp)) { Text(" 7 ") }
           }
           Row {
               Button(onClick = {viewModel.onNumberClicked("8")}, modifier = Modifier.padding(10.dp)) { Text(" 8 ") }
               Button(onClick = {viewModel.onNumberClicked("9")}, modifier = Modifier.padding(10.dp)) { Text(" 9 ") }
               Button(onClick = {viewModel.onClearClicked()}, modifier = Modifier.padding(10.dp)) { Text("Clr") }
               Button(onClick = {viewModel.onEqalstoClicked()}, modifier = Modifier.padding(10.dp)) { Text(" = ") }
           }
       }
   }
}