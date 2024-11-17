package com.example.schhedule

import android.graphics.Rect
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlin.io.path.Path

@Composable
fun ScheduleScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf("Select Platform") }

    val menuItems = listOf("Platform 1", "Platform 2", "Platform 3","Platform 4", "platform 5")



    // Options to display in the dropdown menu



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = selectedItem)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black)
                    .width(160.dp)
            ) {
                menuItems.forEach { item ->
                    CustomDropdownMenuItem(
                        onClick = {
                            selectedItem = item
                            expanded = false
                        },
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        Text(text = item, color =Color.White)
                    }
                }
            }
        }








        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp) // Adjust the height as needed
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray)
                .padding(25.dp),
            contentAlignment = Alignment.Center // Align content to center by default
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f) // Take up the available space above the button
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left Column
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("4:03 PM", style = MaterialTheme.typography.titleSmall, color = Color.White)
                        Text("4:08 PM", style = MaterialTheme.typography.titleSmall, color = Color.White)
                    }

                    // Vertical Line
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .background(Color.White)
                    )

                    // Right Column
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 25.dp) // Optional: Add padding to the right column
                    ) {

                        Text("RAJDHANI EXPR", style = MaterialTheme.typography.titleSmall, color = Color.White)
                        Text("platform 1", style = MaterialTheme.typography.titleSmall, color = Color.White)
                    }
                }

                // Button at the bottom center of the Box
                Button(
                    onClick = { /* Handle button click */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black, // Background color of the button
                        contentColor = Color.White   // Text color of the button
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Center horizontally
                        .padding(top = 16.dp) // Add padding if needed
                ) {
                    Text("Arriving in 05 min,(Moving)")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // New Box with Vertical Line Separator
        Box(
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight()
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
                .padding(16.dp)
        ) {
            Column(
//                modifier = Modifier.fillMaxSize()
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f) // Take up the available space above the button
                        .fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Left Column
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Time: 10:00 AM", style = MaterialTheme.typography.titleSmall)
                        Text("Time: 12:00 PM", style = MaterialTheme.typography.titleSmall)

                        Spacer(modifier = Modifier.height(40.dp))

                        Text("Time: 10:00 AM", style = MaterialTheme.typography.titleSmall)
                        Text("Time: 12:00 PM", style = MaterialTheme.typography.titleSmall)

                        Spacer(modifier = Modifier.height(40.dp))

                        Text("Time: 10:00 AM", style = MaterialTheme.typography.titleSmall)
                        Text("Time: 12:00 PM", style = MaterialTheme.typography.titleSmall)

                        Spacer(modifier = Modifier.height(40.dp))

                        Text("Time: 10:00 AM", style = MaterialTheme.typography.titleSmall)
                        Text("Time: 12:00 PM", style = MaterialTheme.typography.titleSmall)
//                        Spacer(modifier = Modifier.height(30.dp))
//                        Text("Time: 10:00 AM", style = MaterialTheme.typography.titleMedium)
//                        Text("Time: 12:00 PM", style = MaterialTheme.typography.titleMedium)


                    }

                    // Vertical Line
                    Box(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                            .background(Color.White)
                    )

                    // Right Column
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 25.dp) // Optional: Add padding to the right column
                    ) {
                        Text("RAJDHANI EXPR", style = MaterialTheme.typography.titleSmall)
                        Text("platform 1", style = MaterialTheme.typography.titleSmall)

                        Spacer(modifier = Modifier.height(40.dp))

                        Text("RAJDHANI EXPR", style = MaterialTheme.typography.titleSmall)
                        Text("platform 1", style = MaterialTheme.typography.titleSmall)

                        Spacer(modifier = Modifier.height(40.dp))

                        Text("RAJDHANI EXPR", style = MaterialTheme.typography.titleSmall)
                        Text("platform 1", style = MaterialTheme.typography.titleSmall)

                        Spacer(modifier = Modifier.height(40.dp))

                        Text("RAJDHANI EXPR", style = MaterialTheme.typography.titleSmall)
                        Text("platform 1", style = MaterialTheme.typography.titleSmall)
//                        Spacer(modifier = Modifier.height(40.dp))
//                        Text("Platform: 5", style = MaterialTheme.typography.titleMedium)
//                        Spacer(modifier = Modifier.height(40.dp))
//                        Text("Platform: 5", style = MaterialTheme.typography.titleMedium)

                    }




                }
            }

        }
    }
}

@Composable
fun CustomDropdownMenuItem(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    // Box is used to contain the item and handle click interactions
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Customize the visual indication if needed
                onClick = onClick
            )
            .padding(16.dp) // Adjust padding as needed
    ) {
        content()
    }
}