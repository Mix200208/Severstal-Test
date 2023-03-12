package com.app.severstal_test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.app.severstal_test.R
import com.app.severstal_test.data.Task
import com.app.severstal_test.databinding.ActivityAddBinding
import com.app.severstal_test.databinding.ActivityEditBinding
import com.app.severstal_test.vm.TaskViewModel
import com.app.severstal_test.vm.TaskViewModelFactory

class EditActivity : AppCompatActivity() {
    lateinit var obj: Task
    lateinit var taskViewModel: TaskViewModel
    lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
    }

    private fun setUpUi(){

        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit)
        obj = intent.getParcelableExtra<Task>("obj")!!
        binding.editTask.setText(obj.name)
        binding.editDesc.setText(obj.description)

        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(application)).get(TaskViewModel::class.java)
        binding.editBut.apply {
            setOnClickListener {
                editTask()
                startActivity(Intent(this@EditActivity,MainActivity::class.java))
            }
        }

        binding.deleteBut.apply {
            setOnClickListener {
                deleteTask()
                startActivity(Intent(this@EditActivity,MainActivity::class.java))
            }

        }

    }

    private fun editTask(){

        if (validate()){
            val newTask = Task(obj.id, binding.editTask.text.toString(), binding.editDesc.text.toString())
            taskViewModel.editTask(newTask)
        }
    }

    private fun deleteTask(){
        taskViewModel.deleteTraining(obj)
    }

    private fun validate(): Boolean{
        return (binding.editTask.text.isNotEmpty() && binding.editDesc.text.isNotEmpty())
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
    }
}