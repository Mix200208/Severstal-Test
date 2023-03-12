package com.app.severstal_test.repository

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.app.severstal_test.dao.TaskDao
import com.app.severstal_test.data.Task

class TaskRepository(private val taskDao: TaskDao){

    val readAllData: LiveData<List<Task>> = taskDao.getAllData()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    suspend fun editTask(task: Task){
        taskDao.editTask(task)
    }

}