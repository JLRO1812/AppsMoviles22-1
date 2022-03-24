package edu.co.icesi.semana4kotlinv2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter : RecyclerView.Adapter<TaskViewHolder>() {

    private val tasks = ArrayList<Task>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        //Inflate: XML -> view
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.taskrow,parent,false)
        val taskViewHolder = TaskViewHolder(view)
        return taskViewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskn = tasks[position]
        holder.tasktextRow.text = taskn.task
    }

    fun addTask(task:Task){
        tasks.add(task)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


}