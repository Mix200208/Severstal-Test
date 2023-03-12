package com.app.severstal_test.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.app.severstal_test.data.Task
import com.app.severstal_test.database.TaskDataBase
import com.app.severstal_test.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel (application: Application): AndroidViewModel(application) {
    val getAllData: LiveData<List<Task>>
    private val repository: TaskRepository
    init {
        val taskDao = TaskDataBase.getDB(application).taskDao()
        repository = TaskRepository(taskDao)
        getAllData = repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

    fun editTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.editTask(task)
        }
    }

    fun deleteTraining(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }

}