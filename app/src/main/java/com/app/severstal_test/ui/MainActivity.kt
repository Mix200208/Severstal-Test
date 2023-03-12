package com.app.severstal_test.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.severstal_test.R
import com.app.severstal_test.data.Task
import com.app.severstal_test.databinding.ActivityMainBinding
import com.app.severstal_test.rv.TaskListAdapter
import com.app.severstal_test.vm.TaskViewModel
import com.app.severstal_test.vm.TaskViewModelFactory
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    lateinit var taskViewModel: TaskViewModel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpUi()
    }

    fun setUpUi(){

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        taskViewModel = ViewModelProvider(this, TaskViewModelFactory(application)).get(TaskViewModel::class.java)

        val adapter = TaskListAdapter(){
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("obj",it)
            startActivity(intent)
        }
        binding.taskRv.layoutManager = LinearLayoutManager(this)
        binding.taskRv.adapter = adapter


        taskViewModel.getAllData.observe(
            this, Observer { task->
                adapter.setNewData(task as MutableList<Task>)
            }
        )

        binding.addTaskBut.apply {
            setOnClickListener {
                startActivity(Intent(this@MainActivity, AddActivity::class.java))
            }
        }

    }
}