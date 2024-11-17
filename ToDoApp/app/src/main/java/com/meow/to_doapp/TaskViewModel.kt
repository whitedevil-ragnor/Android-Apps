package com.meow.to_doapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository):ViewModel() {
    private val _allTasks=MutableStateFlow<List<Task>>(emptyList())
    val allTasks:StateFlow<List<Task>> =_allTasks

    init {
        getAllTask()
    }
    private fun getAllTask(){
        viewModelScope.launch {
            repository.allTasks.collect{ tasks->
                _allTasks.value=tasks
            }
        }
    }

    fun insertTask(taskName:String){
        viewModelScope.launch {
            val newTask=Task(taskName = taskName)
            repository.insert(newTask)
        }
    }

    fun deleteTask(taskId:Int){
        viewModelScope.launch {
            repository.delete(taskId)
        }
    }
}