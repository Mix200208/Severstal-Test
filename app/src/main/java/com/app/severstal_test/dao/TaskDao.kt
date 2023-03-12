package com.app.severstal_test.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.severstal_test.data.Task

@Dao
interface TaskDao {

    @Insert(entity = Task::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun editTask(task: Task)

    @Query("SELECT * FROM Task ORDER BY id ASC")
    fun getAllData(): LiveData<List<Task>>

}