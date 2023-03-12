package com.app.severstal_test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.severstal_test.R
import com.app.severstal_test.data.Task
import com.app.severstal_test.databinding.ActivityAddBinding
import com.app.severstal_test.vm.TaskViewModel
import com.app.severstal_test.vm.TaskViewModelFactory

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
    }

    fun setUpUi(){

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add)
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(application)).get(TaskViewModel::class.java)

        binding.addBut.apply {
            setOnClickListener {
                if (validate()){
                    insertData()
                    startActivity(Intent(this@AddActivity, MainActivity::class.java))
                }
            }

        }

    }

    private fun validate(): Boolean{
        return (binding.task.text.isNotEmpty() && binding.desc.text.isNotEmpty())
    }

    fun insertData(){
        if (validate()){
            val task = Task(0,binding.task.text.toString(),binding.desc.text.toString())
            taskViewModel.addTask(task)
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
    }
}