package edu.co.icesi.semana3kotlin

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ContactView(itemView:View) : RecyclerView.ViewHolder(itemView) {

    //State
    var contact:Contact?=null

    //Listeners
    var listener: OnContactDelete? = null

    //Identificar los UI components
    var nameRow : TextView = itemView.findViewById(R.id.nameRow)
    var phoneRow : TextView = itemView.findViewById(R.id.phoneRow)
    var imageRow : ImageView = itemView.findViewById(R.id.imageRow)
    var deleteRow: Button = itemView.findViewById(R.id.deleteRow)

    init {
        deleteRow.setOnClickListener{
            contact?.let {
                listener?.onDelete(it)
            }
        }
    }

    interface OnContactDelete{
        fun onDelete(contact: Contact)
    }
}