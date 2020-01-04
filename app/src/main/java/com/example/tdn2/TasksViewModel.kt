package com.example.tdn2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TasksViewModel: ViewModel() {

    val taskListLiveData = MutableLiveData<List<Task>?>()
    private val repository = TaskRepository()

    fun loadTasks() {
        viewModelScope.launch {
            val taskList = repository.loadTasks()
            taskListLiveData.postValue(taskList)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.editTask(task)
            val newList =  taskListLiveData.value?.toMutableList()
            val index = newList?.indexOfFirst { it.id == task.id }
            if (index != null && index >= 0) {
                newList[index] = task
                taskListLiveData.postValue(newList)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            val success = repository.deleteTask(task)

            val newList = taskListLiveData.value.orEmpty().toMutableList()
            if (success == true) {
                newList.remove(task)
            }
            taskListLiveData.postValue(newList)
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {

            val taskAdd = repository.createTask(task)

        }
        }

    }

