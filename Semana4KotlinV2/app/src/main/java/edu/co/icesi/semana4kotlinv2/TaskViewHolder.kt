package edu.co.icesi.semana4kotlinv2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    //UI Controllers
    var tasktextRow:TextView = itemView.findViewById(R.id.tasktextrow)
    //State

    init {

    }
}