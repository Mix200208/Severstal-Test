package com.app.severstal_test.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.severstal_test.data.Task.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val description: String
): Parcelable{
    companion object{
        const val TABLE_NAME = "Task"
    }
}
