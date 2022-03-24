package edu.co.icesi.listasintryro

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactRow(renglon:View): RecyclerView.ViewHolder(renglon){

    var nameRow : TextView = renglon.findViewById(R.id.nameRow)
    var phoneRow : TextView = renglon.findViewById(R.id.phoneRow)
    var dateRow : TextView = renglon.findViewById(R.id.dateRow)



}