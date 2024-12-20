package com.meow.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    // MutableStateFlow to hold the list of tasks
    private val _allTasks = MutableStateFlow<List<Task>>(emptyList())

    // Publicly exposed StateFlow
    val allTasks: StateFlow<List<Task>> = _allTasks

    // Initialize the ViewModel by getting all tasks
    init {
        getAllTasks()
    }

    // Function to collect tasks from the repository
    private fun getAllTasks() {
        viewModelScope.launch {
            repository.allTasks.collect { tasks ->
                _allTasks.value = tasks
            }
        }
    }

    // Function to insert a new task
    fun insertTask(taskName: String) {
        viewModelScope.launch {
            val newTask = Task(taskName = taskName, id = generateUniqueId())
            repository.insert(newTask)
        }
    }

    // Function to delete a task by its ID
    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            repository.delete(taskId)
        }
    }

    private fun generateUniqueId(): Int {
        return (allTasks.value.maxOfOrNull { it.id } ?: 0) + 1
    }
}
