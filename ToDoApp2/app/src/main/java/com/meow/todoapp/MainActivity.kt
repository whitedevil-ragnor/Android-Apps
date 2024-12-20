package com.meow.todoapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.meow.todoapp.ui.theme.ToDoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Room Database and DAO
        val database = TaskDatabase.getDatabase(this)
        val taskDao = database.taskDao()

        // Create TaskRepository
        val taskRepository = TaskRepository(taskDao)

        // Initialize ViewModel with the TaskRepository
        val taskViewModel = ViewModelProvider(this, TaskViewModelFactory(taskRepository)).get(TaskViewModel::class.java)

        setContent {
            ToDoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(taskViewModel = taskViewModel)
                }
            }
        }
    }
}
@Composable
fun MyApp(taskViewModel: TaskViewModel){
    TaskScreen(taskViewModel = taskViewModel)
}
