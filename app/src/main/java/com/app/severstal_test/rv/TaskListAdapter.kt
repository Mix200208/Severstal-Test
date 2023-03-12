package com.app.severstal_test.rv

import android.content.Intent
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.severstal_test.R
import com.app.severstal_test.data.Task

class TaskListAdapter(val listener:(Task) -> Unit): RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {

    private val taskList = mutableListOf<Task>()

    fun setNewData(newData:MutableList<Task>){
        taskList.clear()
        taskList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        return TaskListViewHolder(LayoutInflater.from(parent.context).inflate( R.layout.task_item,parent,false))
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.bind(taskList[position],listener)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class TaskListViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.nameT)
        val desc = view.findViewById<TextView>(R.id.descT)

        fun bind(task: Task, listener: (Task) -> Unit){
            name.text = task.name
            desc.text = task.description
            itemView.setOnClickListener{
                listener(task)
            }
        }

    }
}