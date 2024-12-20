package com.meow.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.meow.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember{ mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters")}
    var outputUnit by remember { mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val convertFactor= remember { mutableStateOf(1.0) }
    val OconvertFactor= remember { mutableStateOf(1.0) }

    fun convert(){
        //elvis opearator
        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result = ((inputValueDouble * convertFactor.value * 100.0 / OconvertFactor.value).roundToInt()) / 100.0
        outputValue=result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       Text("Unit Converter",
           style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange ={
            inputValue=it
            convert()
        }, label = {Text("Enter Value")} )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //input box
            Box{
                //input button
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                        DropdownMenuItem(text = {
                            Text(text = "Centimeter") },
                            onClick = {
                                iExpanded=false
                                inputUnit="Centimeter"
                                convertFactor.value=0.01
                                convert()
                            })
                        DropdownMenuItem(text = {
                            Text(text = "Meter") },
                            onClick = {
                                iExpanded=false
                                inputUnit="Meter"
                                convertFactor.value=1.0
                                convert()
                            })
                        DropdownMenuItem(text = {
                            Text(text = "Feet") },
                            onClick = {
                                iExpanded=false
                                inputUnit="Feet"
                                convertFactor.value=0.3048
                                convert()
                            })
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box {
                //output button
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop down")
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false}) {
                        DropdownMenuItem(text = { Text(text = "Centimeter") },
                            onClick = {
                                oExpanded=false
                                outputUnit="Centimeter"
                                OconvertFactor.value=0.01
                                convert()
                            })
                        DropdownMenuItem(text = { Text(text = "Meter") },
                            onClick = {
                                oExpanded=false
                                outputUnit=="Meter"
                                OconvertFactor.value=1.00
                                convert()
                            })
                        DropdownMenuItem(text = { Text(text = "Feet") },
                            onClick = {
                                oExpanded=false
                                outputUnit="Feet"
                                OconvertFactor.value=0.3048
                                convert()
                            })

                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Rusult: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        UnitConverter()
    }
}