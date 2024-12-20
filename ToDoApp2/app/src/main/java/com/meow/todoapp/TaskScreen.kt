package com.meow.todoapp

import android.graphics.drawable.Icon
import android.widget.ImageButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.IconButton
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.OutlinedTextField
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TaskScreen(taskViewModel: TaskViewModel){
    //collecting all task
    val tasks by taskViewModel.allTasks.collectAsState()
    var newtask by remember { mutableStateOf("") }
    Column( modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Top) {
        Spacer(modifier = Modifier.height(32.dp))
        Row {
            OutlinedTextField(
                value = newtask,
                onValueChange = { newtask=it },
                placeholder = { Text("Enter Task") }, // Placeholder should be a composable
                modifier = Modifier.weight(1f).height(55.dp) // Optional: Make the text field take available space
                , colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White,
                    placeholderColor = Color.DarkGray,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier)
            Button(onClick = {
                if (newtask.isNotBlank()){
                    taskViewModel.insertTask(taskName = newtask)
                    newtask=""
                } },Modifier.width(100.dp).height(55.dp))
            { Text("Add",color=Color.White) }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tasks) { task ->
                TaskItem(
                    task = task,
                    onDelete = { taskViewModel.deleteTask(task.id) }
                )
            }
        }

    }
}

@Composable
fun TaskItem(task: Task, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp), // Padding around each task item
        elevation = 4.dp, // Adds shadow for a raised effect
        backgroundColor = Color.White , contentColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp,4.dp,4.dp,4.dp), // Padding inside the card
            verticalAlignment = Alignment.CenterVertically, // Centers items vertically
            horizontalArrangement = Arrangement.SpaceBetween // Pushes text and button apart
        ) {
            // Task name text
            Text(
                text = task.taskName,
                style = MaterialTheme.typography.titleMedium, // Styling the text
                color = Color.Black // Text color
            )

            // Delete button
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Task",
                    tint = Color.Black // Red color for delete icon
                )
            }
        }
    }
}

