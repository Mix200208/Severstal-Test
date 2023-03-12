package com.app.severstal_test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.severstal_test.dao.TaskDao
import com.app.severstal_test.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var INSTANCE: TaskDataBase? = null

        fun getDB(context: Context): TaskDataBase {
            val tempInstance = INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDataBase::class.java,
                    Task.TABLE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}